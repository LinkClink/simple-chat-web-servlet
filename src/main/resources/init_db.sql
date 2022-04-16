CREATE SCHEMA IF NOT EXISTS `chat` DEFAULT CHARACTER SET utf8;
USE `chat`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
                            `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `user_id` bigint(0) UNSIGNED NOT NULL,
                            `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `send_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
                             `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                             `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;