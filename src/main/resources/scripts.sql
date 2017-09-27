INSERT INTO `t_authority` (`id`, `name`) VALUES ('1', 'ROLE_USER');
INSERT INTO `t_authority` (`id`, `name`) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO `t_conversation_types` (`id`, `name`) VALUES ('1', 'p2p');
INSERT INTO `t_conversation_types` (`id`, `name`) VALUES ('2', 'multi');

INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('1', 'yousef.salaa7@gmail.com', 'Yousef', 'Salah', 'yousef', 'yousef', NULL);
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('2', 'ahmed@gmail.com', 'Ahmed', 'Mohamed', 'ahmed', 'ahmed', NULL);
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('3', 'mohamed@gmail.com', 'mohamed', 'ahmed', 'mohamed', 'mohamed', NULL);
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('4', 'haitham@gmail.com', 'haitham', 'fathy', 'haitham', 'haitham', NULL);
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('5', 'moamen@gmail.com', 'moamen', 'adel', 'moamen', 'moamen', NULL);
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('8', 'sayed@gmail.com', 'sayed', 'sayed', 'sayed', 'sayed', 'yousef.jpg');
INSERT INTO `t_user` (`id`, `email`, `first_name`, `last_name`, `password`, `user_name`, `image_name`) VALUES ('9', 'yaseen@gmail.com', 'yaseen', 'salah', 'yaseen', 'yaseen', 'yousefITI.jpg');

INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('3', 'ffffffff\n', '2017-09-22 12:59:42', '1');
INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('4', 'Yousef Salah (yousef) is wanting to add you as a friend', '2017-09-24 15:01:40', '1');
INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('5', 'Yousef Salah (yousef) is wanting to add you as a friend', '2017-09-22 13:51:43', '1');
INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('6', 'Ahmed Mohamed (ahmed) is wanting to add you as a friend', '2017-09-23 09:17:27', '1');
INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('7', 'kkkkk\n', '2017-09-24 12:40:55', '1');
INSERT INTO `t_conversations` (`id`, `last_message_sent`, `last_message_time`, `conversation_type_id`) VALUES ('8', 'hjhjh\n', '2017-09-24 14:52:44', '2');

INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('3', '3', '2', '2');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('4', '3', '2', '1');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('5', '4', '3', '3');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('6', '4', '3', '1');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('7', '5', '5', '5');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('8', '5', '5', '1');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('9', '6', '4', '4');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('10', '6', '4', '2');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('11', '7', '1', '1');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('12', '7', '1', '8');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('13', '8', '1', '2');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('14', '8', '1', '8');
INSERT INTO `t_conversations_recepients` (`id`, `conversation_id`, `owner_id`, `user_id`) VALUES ('15', '8', '1', '1');

INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('1', '1', '2');
INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('2', '2', '1');
INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('3', '2', '4');
INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('4', '4', '2');
INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('5', '8', '1');
INSERT INTO `t_friends` (`id`, `friend_id`, `user_id`) VALUES ('6', '1', '8');

INSERT INTO `t_message_type` (`id`, `name`) VALUES ('1', 'TEXT');
INSERT INTO `t_message_type` (`id`, `name`) VALUES ('2', 'IMAGE');
INSERT INTO `t_message_type` (`id`, `name`) VALUES ('3', 'VIDEO');
INSERT INTO `t_message_type` (`id`, `name`) VALUES ('4', 'FRIEND_REQUEST');
