package com.chatapp.controllers;

import com.chatapp.domains.User;
import com.chatapp.dtos.AuthenticationResponse;
import com.chatapp.dtos.domains.UserDto;
import com.chatapp.security.JwtAuthenticationRequest;
import com.chatapp.security.JwtTokenUtil;
import com.chatapp.services.AnnounceUserStatus;
import com.chatapp.services.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AuthenticationRestController {

//    @Value("${jwt.header}")
    @Value("Authorization")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AnnounceUserStatus announceUserStatus;

    @Autowired
    private UsersService usersService;

    
    @RequestMapping("isOffline/{userName}")
    public void isOffline(@PathVariable("userName")String userName){
        announceUserStatus.isOffline(userName);
        usersService.getOnlineUsers().remove(userName);
    }
    @RequestMapping(value = "checkUserName/{userName}")
    public boolean isUserNameExists(@PathVariable("userName") String userName) {
        return usersService.isUserNameExists(userName);
    }

    @RequestMapping(value = "checkEmail", method = RequestMethod.POST)
    public boolean isEmailExists(@RequestBody String email) {
        System.out.println("email passed " + email);
        return usersService.isEmailExists(email);
    }

    @RequestMapping(value = "signUp", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "userData") String userData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userData, User.class);
        if (file != null) {
            user.setImageName(file.getOriginalFilename());
        }
        Long userId = usersService.saveUser(user);

        if (file != null) {
            File directory = new File("src/main/resources/profilePics/" + userId);
            directory.mkdir();
            File convFile = new File( directory,file.getOriginalFilename());
            convFile.createNewFile();
            
            FileOutputStream fos = new FileOutputStream(convFile);

            fos.write(file.getBytes());
            fos.close();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(true, "Registered successfully !", null, null));
    }

    @RequestMapping(value = "auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        boolean result = usersService.validateUserNameAndPassword(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
        UserDto userDto = usersService.getUserByUserNameAndPassword(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
//        System.out.println("user dto" + userDto.getEmail());
        if (result) {
            // Perform the security
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(upat);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Reload password post-security so we can generate token
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails, device);
            System.out.println("token " + token);
//            announceUserStatus.isOnline(authenticationRequest.getUsername());
            // Return the token
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(true, "login succeeded", token, userDto));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(false, "Username or Password is not correct"));
//            return ResponseEntity.ok();
        }
    }

}
