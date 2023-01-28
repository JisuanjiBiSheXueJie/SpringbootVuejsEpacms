/*
 Navicat Premium Data Transfer

 Source Server         : localMysql8
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : epacms

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 28/01/2023 20:04:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `t_exception_log`;
CREATE TABLE `t_exception_log` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `exp_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '异常名称',
  `exp_msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '异常信息',
  `req_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求参数',
  `req_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `req_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求URI',
  `req_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求IP',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '异常发生时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_exception_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `class_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '班级号',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_grade
-- ----------------------------
BEGIN;
INSERT INTO `t_grade` (`id`, `class_num`, `create_time`) VALUES (1, '1806801', '2022-05-14 20:37:07');
INSERT INTO `t_grade` (`id`, `class_num`, `create_time`) VALUES (2, '1806802', '2022-05-14 20:38:21');
INSERT INTO `t_grade` (`id`, `class_num`, `create_time`) VALUES (3, '1806803', '2022-05-14 20:38:41');
INSERT INTO `t_grade` (`id`, `class_num`, `create_time`) VALUES (4, '1806804', '2022-05-14 20:39:16');
COMMIT;

-- ----------------------------
-- Table structure for t_instructor_student
-- ----------------------------
DROP TABLE IF EXISTS `t_instructor_student`;
CREATE TABLE `t_instructor_student` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `instructor_id` int unsigned DEFAULT NULL,
  `student_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `instructor_id` (`instructor_id`,`student_id`) USING BTREE,
  UNIQUE KEY `student_id` (`student_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_instructor_student
-- ----------------------------
BEGIN;
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (1, 2, 20);
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (2, 2, 22);
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (5, 13, 21);
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (4, 13, 23);
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (6, 13, 34);
INSERT INTO `t_instructor_student` (`id`, `instructor_id`, `student_id`) VALUES (7, 13, 35);
COMMIT;

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int unsigned DEFAULT NULL COMMENT '用户id',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '离校原因',
  `trajectory` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行程轨迹',
  `status` smallint DEFAULT '1' COMMENT '审批状态',
  `leave_time` timestamp NULL DEFAULT NULL COMMENT '离校时间',
  `return_time` timestamp NULL DEFAULT NULL COMMENT '返回时间',
  `apply_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
BEGIN;
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (7, 23, '生病了...', '天津市>河东区', 3, '2022-04-14 00:00:00', '2022-04-15 00:00:00', '2022-02-14 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (8, 20, 'xxxxxxxx', '河北省>邯郸市>复兴区', 2, '2022-03-28 00:00:00', '2022-04-28 00:00:00', '2022-04-17 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (9, 23, '回家', '内蒙古自治区>乌海市>海南区', 2, '2022-04-22 00:00:00', '2022-04-26 00:00:00', '2022-04-22 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (10, 23, '回家', '河北省>邯郸市>峰峰矿区', 2, '2022-04-21 00:00:00', '2022-04-29 00:00:00', '2022-04-22 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (11, 20, '打csgo', '河南省>洛阳市>西工区', 2, '2022-05-06 00:00:00', '2022-05-17 00:00:00', '2022-05-06 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (12, 22, '回家', '江苏省>常州市>钟楼区', 1, '2022-05-07 00:00:00', '2022-05-17 00:00:00', '2022-05-08 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (13, 21, '回家休息', '河北省>秦皇岛市>北戴河区', 3, '2022-05-16 00:00:00', '2022-05-18 00:00:00', '2022-05-08 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (15, 35, 'xxxxx', '福建省>厦门市>思明区', 2, '2022-05-12 00:00:00', '2022-05-13 00:00:00', '2022-05-13 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (16, 21, '123', '天津市河西区', 2, '2022-05-17 00:00:00', '2022-05-26 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (18, 21, '123', '山西省阳泉市郊区', 2, '2022-05-17 00:00:00', '2022-05-26 00:00:00', '2022-05-18 00:00:00');
INSERT INTO `t_leave` (`id`, `uid`, `reason`, `trajectory`, `status`, `leave_time`, `return_time`, `apply_time`) VALUES (19, 34, '回家休息几天', '河北省邯郸市复兴区', 3, '2022-05-19 00:00:00', '2022-05-22 00:00:00', '2022-05-19 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '新闻标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '新闻URL',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '摘录日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_news
-- ----------------------------
BEGIN;
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (5, '6家方舱医院在长春投入使用', 'http://health.people.com.cn/n1/2022/0324/c14739-32382880.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (13, '五问新冠抗原检测', 'http://health.people.com.cn/n1/2022/0321/c14739-32379723.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (14, '疫情有反复 健身不间断', 'http://health.people.com.cn/n1/2022/0321/c14739-32379972.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (15, '动态清零的精髓是快速和精准', 'http://health.people.com.cn/n1/2022/0321/c14739-32380319.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (16, '居民如何进行新冠病毒抗原自测？这期教程要收好！', 'http://health.people.com.cn/n1/2022/0317/c14739-32377121.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (21, '孕妈接种新冠疫苗 宝宝也有抗体', 'http://health.people.com.cn/n1/2022/0210/c14739-32349181.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (22, '奥密克戎感染人类细胞的细节揭示', 'http://health.people.com.cn/n1/2022/0124/c14739-32338117.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (23, '中疾控专家：清明不建议安排跨省区的传统祭扫活动', 'http://health.people.com.cn/n1/2022/0401/c14739-32390137.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (24, '国家卫健委：我国全程接种人数占全国总人口的88.11% 鼓励老…', 'http://health.people.com.cn/n1/2022/0401/c14739-32390135.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (25, '为何要坚持动态清零？中疾控专家详解', 'http://health.people.com.cn/n1/2022/0401/c14739-32390113.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (26, '@广大老年人 还在担心接种新冠疫苗的不良反应？权威分析都在这里', 'http://health.people.com.cn/n1/2022/0325/c14739-32384413.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (27, '专家谈第九版诊疗方案：结合奥密克戎的特点进行了多处修订', 'http://health.people.com.cn/n1/2022/0325/c14739-32384409.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (28, '国家卫健委：我国2.12亿60岁以上老年人完成新冠病毒疫苗全程…', 'http://health.people.com.cn/n1/2022/0325/c14739-32384408.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (29, '“时空伴随者”如何筛查？抗原检测能否替代48小时核酸检测？权威…', 'http://health.people.com.cn/n1/2022/0325/c14739-32384401.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (30, '国家卫健委：3月1日-24日全国累计报告本土感染者已超5600…', 'http://health.people.com.cn/n1/2022/0325/c14739-32384400.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (31, '国家卫健委：我国全程接种人数占全国总人口的88.11% …', 'http://health.people.com.cn/n1/2022/0401/c14739-32390135.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (32, '近期多起疫情为何隐匿传播时间较长？国家卫健委揭秘', 'http://health.people.com.cn/n1/2022/0115/c14739-32332114.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (33, '奥密克戎变异株不影响我国现有核酸检测试剂的敏感性和特异性', 'http://health.people.com.cn/n1/2022/0115/c14739-32332112.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (34, '国家卫健委：广东珠海、中山及上海存在社区感染传播外溢风险', 'http://health.people.com.cn/n1/2022/0115/c14739-32332109.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (35, '国家卫健委：我国面临德尔塔和奥密克戎毒株输入疫情双重挑战…', 'http://health.people.com.cn/n1/2022/0115/c14739-32332108.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (36, '国家卫健委发布新冠疫苗加强针十问十答：使用已接种过的原疫…', 'http://health.people.com.cn/n1/2021/1116/c14739-32283576.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (37, '权威人士详解中国新冠病毒疫苗 保护效力符合世卫组织要求', 'http://health.people.com.cn/n1/2021/0413/c14739-32076502.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (38, '让新冠疫苗真正成为“人民的疫苗”', 'http://health.people.com.cn/n1/2021/0328/c14739-32062814.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (39, '江苏南京鼓楼区新增一例确诊病例？官方深夜辟谣', 'http://health.people.com.cn/n1/2020/0706/c14739-31772102.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (40, '新冠病毒变异武汉几十人中招？武汉市卫健委：谣言', 'http://health.people.com.cn/n1/2020/0702/c14739-31767933.html', '2022-04-02 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (52, '3月28日（0-24时）本市各区确诊病例、无症状感染者居住地信息', 'https://wsjkw.sh.gov.cn/xwfb/20220329/b7430a2b3e04483c9d7ac6e4f4d4cf68.html', '2022-04-03 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (53, '昨日新增本土新冠肺炎确诊病例96例、无症状感染者4381例，新增境外输入性确诊病例11例、无症状感染者1例', 'https://wsjkw.sh.gov.cn/xwfb/20220329/147ddbec518c4d26b5b4a4492dbf0650.html', '2022-04-03 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (61, '广州多个接种点现场排长龙', 'http://health.people.com.cn/n1/2021/0528/c14739-32115574.html', '2022-05-18 00:00:00');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (73, '国家疾控局：多措并举 为老年人提供更好的新冠疫苗…', 'http://health.people.com.cn/n1/2022/1129/c14739-32577042.html', '2022-12-03 14:20:36');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (75, '被污染但不能消毒的物品和环境应该如何处理？中疾控…', 'http://health.people.com.cn/n1/2022/1129/c14739-32577034.html', '2022-12-03 14:37:22');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (76, '中疾控：居家隔离人员的共同居住者要一并遵守居家隔…', 'http://health.people.com.cn/n1/2022/1129/c14739-32577027.html', '2022-12-03 14:37:22');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (77, '坚定不移贯彻“动态清零”总方针', 'http://health.people.com.cn/n1/2022/1115/c14739-32566174.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (78, '更加科学精准做好防控工作', 'http://health.people.com.cn/n1/2022/1114/c14739-32565270.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (79, '进一步优化疫情防控措施 风险区高中低调整为高低', 'http://health.people.com.cn/n1/2022/1112/c14739-32564693.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (80, '国家卫健委：二十条优化措施不是放松防控 更不是放…', 'http://health.people.com.cn/n1/2022/1112/c14739-32564764.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (81, '为什么改成“5+3”“不再判定次密接”？国家疾控…', 'http://health.people.com.cn/n1/2022/1112/c14739-32564773.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (82, '国家疾控局：二十条优化措施未提及的都按第九版来执行', 'http://health.people.com.cn/n1/2022/1112/c14739-32564783.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (83, '国家卫健委：将通过一系列举措推动二十条优化措施落…', 'http://health.people.com.cn/n1/2022/1112/c14739-32564785.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (84, '国家疾控局：二十条优化措施未提及的都按第九版来执行', 'http://health.people.com.cn/n1/2022/1112/c14739-32564783.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (85, '国务院联防联控机制权威回应二十条优化措施调整看点', 'http://health.people.com.cn/n1/2022/1112/c14739-32564821.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (86, '二十条优化措施基于科学认识（国务院联防联控机制发…', 'http://health.people.com.cn/n1/2022/1113/c14739-32564839.html', '2022-12-03 14:52:40');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (87, '农村地区是当前疫情防控的重中之重', 'http://health.people.com.cn/n1/2023/0119/c14739-32610114.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (88, '', 'http://health.people.com.cn#', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (89, '中疾控：我国尚未监测到“德尔塔克戎”变异株', 'http://health.people.com.cn/n1/2023/0119/c14739-32610131.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (90, '国家卫健委：各省已度过发热门诊、急诊和重症患者三…', 'http://health.people.com.cn/n1/2023/0119/c14739-32610142.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (91, '截至1月19日，全国基层医疗卫生机构共配备247…', 'http://health.people.com.cn/n1/2023/0119/c14739-32610152.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (92, '交通运输部：截至1月18日 全国铁路、公路、水路…', 'http://health.people.com.cnhttp://finance.people.com.cn/n1/2023/0119/c1004-32610118.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (93, '如何降低返乡途中疫情传播风险？交通运输部介绍', 'http://health.people.com.cnhttp://finance.people.com.cn/n1/2023/0119/c1004-32610157.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (94, '文旅部：要求文化和旅游经营场所做好常态化防控 降…', 'http://health.people.com.cnhttp://ent.people.com.cn/n1/2023/0119/c1012-32610119.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (95, '文旅部：已向深圳、郑州等旅游热度较高城市发布预报…', 'http://health.people.com.cnhttp://ent.people.com.cn/n1/2023/0119/c1012-32610162.html', '2023-01-20 15:11:41');
INSERT INTO `t_news` (`id`, `title`, `url`, `create_time`) VALUES (96, '农业农村部：在农村进行全面摸排 摸清老幼病残孕等…', 'http://health.people.com.cn/n1/2023/0119/c14739-32610127.html', '2023-01-20 15:11:41');
COMMIT;

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `uid` int unsigned NOT NULL COMMENT '用户id',
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '标题',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '来源',
  `detail_md` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '详细内容（md）',
  `click_num` int unsigned DEFAULT '0' COMMENT '点击数',
  `detail_html` longtext CHARACTER SET utf8 COLLATE utf8_bin COMMENT '详细内容（html）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
BEGIN;
INSERT INTO `t_notice` (`id`, `uid`, `title`, `source`, `detail_md`, `click_num`, `detail_html`, `create_time`, `update_time`) VALUES (7, 13, '关于从严从紧落实疫情防控措施的紧急通知', '无', '校属各单位：\n\n近期，我国疫情多发频发，疫情防控形势复杂严峻。为深入贯彻落实全国全省疫情防控工作视频会议精神以及教育部紧急通知，从严从紧科学精准做好我校疫情防控工作，确保师生员工生命健康和校园安全稳定，根据省教育厅《关于从严从紧做好当前学校疫情防控工作的通知》（湘教通﹝2022﹞40号）文件精神，学校决定立即启动疫情防控应急机制，在严格执行学校《湖南城市学院新冠肺炎防控应急预案》情况下，就进一步强化校园疫情防控措施通知如下：\n\n**一、坚决扛牢政治责任**\n\n各单位要深刻认识到当前疫情防控形势的严峻复杂性，以及当前流行奥密克戎变异株隐匿传染性强的严重性，充分汲取部分地区发生校园聚集性疫情的深刻教训，思想上绝对不能麻痹松懈，行动上要稳健果断，切实把抓好学校疫情防控工作作为当前最重要的政治任务，把思想和行动统一到党中央、国务院以及省委省政府的决策部署上来，统筹推进学校系统疫情防控和学校改革发展。\n\n**二、切实强化组织领导**\n\n学校成立疫情防控工作领导小组办公室，设在党政办公室，具体负责学校疫情防控工作的总体协调。学校疫情防控工作办公室、学生防控与管理组、校园管控与后勤保障组、宣传组织与职工管理组、督查工作组要积极主动开展工作，切实履行疫情防控工作主要职责；党政办负责做好总体协调工作。各单位要严格履行好主体责任，结合各单位实际创造性开展工作，确保学校疫情防控措施落地落实见效，并教育引导好员工落实个人防护责任。二级学院要完善学校-学院-班级三级快速反应机制，严格落实执行师生员工的外出请假制度，建立好工作台账。学院党政主要负责人和部门负责人要切实履行疫情防控第一责任人职责。坚持守土有责、守土担责、守土尽责，筑牢校园疫情防控屏障，确保校园疫情防控万无一失。\n\n**三、加强校园封闭式管理**\n\n校外无关人员和车辆一律不得入校。确有必要进入校园者，须经校内相关部门办理预约，在校门处提供48小时核酸检测阴性证明、测体温、查验健康码、行程卡无异常，扫场所码并登记详细信息后方可进入学校。校外考生进入学校参加考试，按照学校专门疫情防控方案要求执行。师生员工非必要不离益阳、不离湖南。确需离益阳、离湖南的，必须按规定办理报批手续，并按要求落实防控措施；返校进入校园时，须提前向所属单位及学校疫情防控工作办公室报备。学校对进入校园的人员一律测体温、查验健康码、行程卡无异常后，扫场所码方可放行。\n\n**四、加强对重点人群、场所和各种聚集性活动管控**\n\n加强对各类临聘人员的管理。餐饮、保洁、保安、基建等后勤保障人员等必须完成疫苗全程接种和加强针接种，进行每日“健康打卡”并查验健康码、行程卡。各类临聘人员定期进行核酸检测，除餐饮之外的其他人员每周开展一次核酸检测。健康码、行程卡和核酸检测等信息出现异常的人员，要严格按疫情防控要求执行相关措施。\n\n密切关注校外实习实训学生。各二级学院必须以班为单位建立校外实习实训学生和暂未返校学生管理台账，密切关注其身体健康状况及所在地区的疫情形势变化，切实加强实习实训学生和暂未返校学生的疫情防控工作。\n\n严格食堂用餐管理。师生员工按要求错时、错峰在食堂分散用餐，避免集中聚集性就餐。排队取餐时应佩戴口罩，并保持适当距离，用餐完毕尽快离开食堂。食堂全体工作人员在岗期间要保持佩戴口罩、手套，每天按规定及时更换。\n\n加强校园快递管理。校园内快递服务点的相关工作人员应完成疫苗加强针接种，每日查码测温，进行健康打卡，定期进行核酸检测。快递服务点对收发快递场所及快递件进行严格消毒，对快递取件人员落实戴口罩、1米线、少逗留等要求。\n\n严格管控各种聚集性活动。各单位一律暂停校外或跨区域、跨省集体活动。非必要不举行有校外人员参加的聚集性活动。确需举办的，按照“谁组织谁负责，谁审批谁负责”和“一活动一方案”原则认真制定疫情防控工作方案和应急预案，并按规定报批。\n\n**五、加快推进疫苗接种**\n\n各单位要积极推进师生员工的加强针接种工作。在坚持“知情、同意、自愿”原则的同时，做到“能接快接、应接尽接”。对师生员工疫苗接种情况进行全面排查，建立工作台账，对接种率较低的各二级单位进行专门督查、约谈。\n\n**六、提升应急处置能力**\n\n健全并进一步完善学校突发疫情的应急处置预案，开展应急演练，确保应急预案能够随时启动。在学校南门、北门建立紧急处置室，用于紧急隔离查码、测温时发现的异常人员。与相关酒店迅速签订租用合同，备用50间隔离房间用于过渡、居家健康监测、留观使用。\n\n**七、规范日常防控工作**\n\n加强每日“健康打卡”情况管理。师生员工坚持每天按时健康打卡，每日查看健康码和行程码，动态掌握身体健康状况。严格落实学生因病缺课病因追踪登记制度，学生病假复课需医疗机构开具的复课证明。坚决落实“日报告”“零报告”等制度，如出现疑似病例、确诊病例、确诊病例密切接触者等情况，须第一时间报告。\n\n加强校园环境消毒。加强校园环境清洁和消毒力度，对教室、图书馆、实验室、会议室等重点场所进行日常预防性消毒。加强所有室内场所通风换气，对教室、宿舍等室内场所每天至少2次开窗通风换气,每次至少1小时。\n\n**八、加强督促检查**\n\n将疫情防控工作纳入学校年度绩效考核。紧盯疫情期间师生员工的责任担当和履职尽责，纪委办公室/省监委驻校监察专员办/党委巡察督察办公室将联合党政办、组织部、人事处、学生工作部等部门，对校门管理松懈、防控措施不细不严、重点人员健康监测及核酸检测不到位等问题，组织开展每周2次的专项督查，对责任意识淡薄、疫情防控责任落实不力、违反疫情防控纪律的部门和师生员工进行通报，情节严重的，依法依规进行追责。\n\n \n::: hljs-right\n\n\n湖南城市学院\n2022年3月16日\n\n:::\n', 11, '<p>校属各单位：</p>\n<p>近期，我国疫情多发频发，疫情防控形势复杂严峻。为深入贯彻落实全国全省疫情防控工作视频会议精神以及教育部紧急通知，从严从紧科学精准做好我校疫情防控工作，确保师生员工生命健康和校园安全稳定，根据省教育厅《关于从严从紧做好当前学校疫情防控工作的通知》（湘教通﹝2022﹞40号）文件精神，学校决定立即启动疫情防控应急机制，在严格执行学校《湖南城市学院新冠肺炎防控应急预案》情况下，就进一步强化校园疫情防控措施通知如下：</p>\n<p><strong>一、坚决扛牢政治责任</strong></p>\n<p>各单位要深刻认识到当前疫情防控形势的严峻复杂性，以及当前流行奥密克戎变异株隐匿传染性强的严重性，充分汲取部分地区发生校园聚集性疫情的深刻教训，思想上绝对不能麻痹松懈，行动上要稳健果断，切实把抓好学校疫情防控工作作为当前最重要的政治任务，把思想和行动统一到党中央、国务院以及省委省政府的决策部署上来，统筹推进学校系统疫情防控和学校改革发展。</p>\n<p><strong>二、切实强化组织领导</strong></p>\n<p>学校成立疫情防控工作领导小组办公室，设在党政办公室，具体负责学校疫情防控工作的总体协调。学校疫情防控工作办公室、学生防控与管理组、校园管控与后勤保障组、宣传组织与职工管理组、督查工作组要积极主动开展工作，切实履行疫情防控工作主要职责；党政办负责做好总体协调工作。各单位要严格履行好主体责任，结合各单位实际创造性开展工作，确保学校疫情防控措施落地落实见效，并教育引导好员工落实个人防护责任。二级学院要完善学校-学院-班级三级快速反应机制，严格落实执行师生员工的外出请假制度，建立好工作台账。学院党政主要负责人和部门负责人要切实履行疫情防控第一责任人职责。坚持守土有责、守土担责、守土尽责，筑牢校园疫情防控屏障，确保校园疫情防控万无一失。</p>\n<p><strong>三、加强校园封闭式管理</strong></p>\n<p>校外无关人员和车辆一律不得入校。确有必要进入校园者，须经校内相关部门办理预约，在校门处提供48小时核酸检测阴性证明、测体温、查验健康码、行程卡无异常，扫场所码并登记详细信息后方可进入学校。校外考生进入学校参加考试，按照学校专门疫情防控方案要求执行。师生员工非必要不离益阳、不离湖南。确需离益阳、离湖南的，必须按规定办理报批手续，并按要求落实防控措施；返校进入校园时，须提前向所属单位及学校疫情防控工作办公室报备。学校对进入校园的人员一律测体温、查验健康码、行程卡无异常后，扫场所码方可放行。</p>\n<p><strong>四、加强对重点人群、场所和各种聚集性活动管控</strong></p>\n<p>加强对各类临聘人员的管理。餐饮、保洁、保安、基建等后勤保障人员等必须完成疫苗全程接种和加强针接种，进行每日“健康打卡”并查验健康码、行程卡。各类临聘人员定期进行核酸检测，除餐饮之外的其他人员每周开展一次核酸检测。健康码、行程卡和核酸检测等信息出现异常的人员，要严格按疫情防控要求执行相关措施。</p>\n<p>密切关注校外实习实训学生。各二级学院必须以班为单位建立校外实习实训学生和暂未返校学生管理台账，密切关注其身体健康状况及所在地区的疫情形势变化，切实加强实习实训学生和暂未返校学生的疫情防控工作。</p>\n<p>严格食堂用餐管理。师生员工按要求错时、错峰在食堂分散用餐，避免集中聚集性就餐。排队取餐时应佩戴口罩，并保持适当距离，用餐完毕尽快离开食堂。食堂全体工作人员在岗期间要保持佩戴口罩、手套，每天按规定及时更换。</p>\n<p>加强校园快递管理。校园内快递服务点的相关工作人员应完成疫苗加强针接种，每日查码测温，进行健康打卡，定期进行核酸检测。快递服务点对收发快递场所及快递件进行严格消毒，对快递取件人员落实戴口罩、1米线、少逗留等要求。</p>\n<p>严格管控各种聚集性活动。各单位一律暂停校外或跨区域、跨省集体活动。非必要不举行有校外人员参加的聚集性活动。确需举办的，按照“谁组织谁负责，谁审批谁负责”和“一活动一方案”原则认真制定疫情防控工作方案和应急预案，并按规定报批。</p>\n<p><strong>五、加快推进疫苗接种</strong></p>\n<p>各单位要积极推进师生员工的加强针接种工作。在坚持“知情、同意、自愿”原则的同时，做到“能接快接、应接尽接”。对师生员工疫苗接种情况进行全面排查，建立工作台账，对接种率较低的各二级单位进行专门督查、约谈。</p>\n<p><strong>六、提升应急处置能力</strong></p>\n<p>健全并进一步完善学校突发疫情的应急处置预案，开展应急演练，确保应急预案能够随时启动。在学校南门、北门建立紧急处置室，用于紧急隔离查码、测温时发现的异常人员。与相关酒店迅速签订租用合同，备用50间隔离房间用于过渡、居家健康监测、留观使用。</p>\n<p><strong>七、规范日常防控工作</strong></p>\n<p>加强每日“健康打卡”情况管理。师生员工坚持每天按时健康打卡，每日查看健康码和行程码，动态掌握身体健康状况。严格落实学生因病缺课病因追踪登记制度，学生病假复课需医疗机构开具的复课证明。坚决落实“日报告”“零报告”等制度，如出现疑似病例、确诊病例、确诊病例密切接触者等情况，须第一时间报告。</p>\n<p>加强校园环境消毒。加强校园环境清洁和消毒力度，对教室、图书馆、实验室、会议室等重点场所进行日常预防性消毒。加强所有室内场所通风换气，对教室、宿舍等室内场所每天至少2次开窗通风换气,每次至少1小时。</p>\n<p><strong>八、加强督促检查</strong></p>\n<p>将疫情防控工作纳入学校年度绩效考核。紧盯疫情期间师生员工的责任担当和履职尽责，纪委办公室/省监委驻校监察专员办/党委巡察督察办公室将联合党政办、组织部、人事处、学生工作部等部门，对校门管理松懈、防控措施不细不严、重点人员健康监测及核酸检测不到位等问题，组织开展每周2次的专项督查，对责任意识淡薄、疫情防控责任落实不力、违反疫情防控纪律的部门和师生员工进行通报，情节严重的，依法依规进行追责。</p>\n<div class=\"hljs-right\">\n<p>湖南城市学院<br />\n2022年3月16日</p>\n</div>\n', '2022-11-21 21:30:10', '2023-01-20 15:06:45');
INSERT INTO `t_notice` (`id`, `uid`, `title`, `source`, `detail_md`, `click_num`, `detail_html`, `create_time`, `update_time`) VALUES (8, 1, '师生志愿者集结 助力疫情防控', '湖南城市学院', '疫情就是命令，防控就是责任。3月以来，新一轮疫情来势凶猛，面对复杂严峻的疫情形势和繁重的防控任务，湖南城市学院党委号召：党员领导干部要冲在抗疫最前沿，充分发挥基层组织的战斗堡垒作用！各级组织立即响应，党员教师和团员青年化身志愿者战斗在“抗疫”第一线，将“初心”写在行动上，将“使命”落在岗位上，用爱心与奉献凝聚起战胜疫情的强大力量，绘就了一幅同心战疫的精彩画卷。', 6, '<p>疫情就是命令，防控就是责任。3月以来，新一轮疫情来势凶猛，面对复杂严峻的疫情形势和繁重的防控任务，湖南城市学院党委号召：党员领导干部要冲在抗疫最前沿，充分发挥基层组织的战斗堡垒作用！各级组织立即响应，党员教师和团员青年化身志愿者战斗在“抗疫”第一线，将“初心”写在行动上，将“使命”落在岗位上，用爱心与奉献凝聚起战胜疫情的强大力量，绘就了一幅同心战疫的精彩画卷。</p>\n', '2022-05-08 00:00:00', '2022-11-25 21:51:57');
COMMIT;

-- ----------------------------
-- Table structure for t_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operation_log`;
CREATE TABLE `t_operation_log` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作类型',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '操作描述',
  `module` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '功能模块',
  `operation_time` timestamp NULL DEFAULT NULL COMMENT '操作时间',
  `req_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '请求参数',
  `resp_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '返回参数',
  `req_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `req_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求URI',
  `req_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_operation_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资源路径',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (1, 'home', '/home', '我的主页', 's-home');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (2, 'personinfo', '/personinfo', '个人信息', 'user');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (3, '/permission', '/permission', '系统权限', 'key');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (4, 'user', '/user', '用户管理', 'user-solid');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (5, 'notice', '/notice', '通知管理', 'phone-outline');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (6, 'leave', '/leave', '离校管理', 'office-building');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (7, 'health', '/health', '健康打卡', 'warning-outline');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (8, 'news', '/news', '新闻管理', 'news');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (9, 'file', '/file', '文件管理', 'files');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (10, 'statistics', '/statistics', '统计分析', 's-data');
INSERT INTO `t_permission` (`id`, `name`, `path`, `description`, `icon`) VALUES (11, 'epidemic', '/epidemic', '全国疫情', 'pie-chart');
COMMIT;

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `uid` int unsigned DEFAULT NULL COMMENT '用户id',
  `position` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '位置',
  `temperature` double DEFAULT NULL COMMENT '体温',
  `cough` int DEFAULT NULL COMMENT '是否咳嗽',
  `fever` int DEFAULT NULL COMMENT '是否发烧',
  `time` datetime DEFAULT NULL COMMENT '打卡时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_record
-- ----------------------------
BEGIN;
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (25, 20, '山西省>长治市>长治县', 36.9, 0, 0, '2022-04-11 18:39:37');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (32, 22, '河北省>邢台市>临城县', 36.7, 0, 0, '2022-04-13 07:54:09');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (34, 20, '山西省>长治市>襄垣县', 37.3, 0, 1, '2022-04-13 10:06:25');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (35, 20, '天津市>河西区', 36.9, 0, 0, '2022-04-13 14:27:08');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (46, 20, '湖南省>益阳市>赫山区', 37.2, 0, 0, '2022-05-06 14:48:24');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (47, 20, '河北省>秦皇岛市>抚宁区', 36.8, 0, 1, '2022-05-06 14:54:51');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (48, 20, '山西省>阳泉市>郊区', 37, 0, 0, '2022-05-06 21:21:18');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (49, 20, '湖南省益阳市赫山区迎宾东路', 37.2, 1, 0, '2022-05-06 22:39:37');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (50, 20, '湖南省益阳市', 37.1, 0, 0, '2022-05-06 22:51:29');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (53, 21, '湖南省益阳市赫山区迎宾东路', 37, 0, 0, '2022-05-07 09:03:32');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (54, 21, '湖南省益阳市赫山区迎宾东路', 37.1, 0, 0, '2022-05-07 09:03:38');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (56, 20, '湖南省益阳市', 37.1, 0, 1, '2022-05-07 13:57:18');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (57, 20, '湖南省益阳市', 36.9, 0, 0, '2022-05-07 13:58:35');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (58, 20, '湖南省益阳市赫山区迎宾东路', 37.1, 1, 0, '2022-05-07 14:00:14');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (59, 20, '湖南省益阳市赫山区迎宾东路', 36.9, 1, 1, '2022-05-07 18:39:36');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (60, 22, '湖南省益阳市赫山区迎宾东路', 37, 0, 0, '2022-05-07 19:11:48');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (69, 34, '湖南省益阳市赫山区申城西路', 36.8, 0, 0, '2022-05-08 12:31:26');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (70, 20, '湖南省益阳市赫山区申城西路', 36.8, 0, 0, '2022-05-08 12:32:06');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (71, 23, '湖南省益阳市赫山区申城西路', 36.9, 0, 1, '2022-05-08 12:32:35');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (72, 22, '湖南省益阳市赫山区申城西路', 36.8, 1, 0, '2022-05-08 12:33:05');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (76, 21, '湖南省益阳市', 36.8, 0, 0, '2022-05-08 15:32:31');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (77, 21, '湖南省益阳市赫山区迎宾东路', 36.8, 0, 0, '2022-05-13 14:52:49');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (78, 35, '湖南省益阳市赫山区迎宾东路', 36.8, 0, 1, '2022-05-13 15:19:27');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (79, 35, '湖南省长沙市岳麓区', 36.8, 0, 1, '2022-05-14 22:38:24');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (80, 22, '湖南省益阳市', 36.9, 0, 1, '2022-05-16 07:49:41');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (81, 22, '湖南省长沙市岳麓区', 36.9, 0, 0, '2022-05-17 15:54:35');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (82, 21, '湖南省益阳市', 36.7, 0, 1, '2022-05-17 15:55:25');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (83, 23, '湖南省益阳市赫山区迎宾东路', 36.9, 0, 1, '2022-05-17 19:26:25');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (84, 23, '湖南省益阳市赫山区迎宾东路', 36.9, 0, 1, '2022-05-18 08:01:34');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (85, 20, '湖南省益阳市赫山区迎宾东路', 36.9, 0, 0, '2022-05-18 08:03:18');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (87, 21, '湖南省益阳市赫山区迎宾东路', 36.9, 0, 1, '2022-05-18 14:41:01');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (88, 22, '湖南省益阳市赫山区迎宾东路', 37.5, 0, 1, '2022-05-18 21:52:43');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (89, 34, '湖南省益阳市', 36.9, 0, 1, '2022-05-18 21:54:15');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (91, 34, '湖南省益阳市赫山区迎宾东路', 36.8, 0, 1, '2022-05-19 09:27:20');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (92, 23, '湖南省益阳市', 37, 1, 1, '2022-05-19 10:26:38');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (93, 20, '湖南省长沙市岳麓区', 36.8, 0, 0, '2022-05-20 08:25:31');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (94, 21, '湖南省益阳市赫山区迎宾东路', 36.8, 0, 1, '2022-05-20 08:45:50');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (95, 22, '湖南省益阳市', 36.7, 0, 0, '2022-05-20 08:46:50');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (96, 22, '湖南省益阳市', 36.7, 0, 0, '2022-01-01 10:30:17');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (97, 34, '湖南省益阳市', 36.7, 0, 0, '2022-02-01 10:30:46');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (98, 34, '湖南省益阳市', 36.7, 0, 0, '2022-04-01 10:30:54');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (99, 21, '湖南省益阳市', 37.1, 0, 0, '2022-02-01 10:33:35');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (100, 23, '湖南省益阳市', 36.7, 0, 0, '2022-01-01 10:31:50');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (101, 23, '湖南省益阳市', 36.7, 0, 0, '2022-03-01 10:32:02');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (103, 22, '湖南省益阳市', 36.7, 0, 0, '2022-02-01 10:32:25');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (104, 21, '湖南省益阳市', 36.7, 0, 0, '2022-02-01 10:34:09');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (106, 34, '湖南省益阳市', 36.7, 0, 0, '2022-03-01 10:34:27');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (107, 14, '湖南省益阳市赫山区迎宾东路', 36.9, 0, 0, '2022-05-20 13:02:29');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (108, 14, '湖南省益阳市', 36.8, 1, 0, '2022-05-21 14:52:06');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (109, 20, '湖南省益阳市', 36.9, 0, 0, '2022-05-21 15:07:11');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (110, 20, '福建省厦门市思明区', 36.8, 0, 0, '2022-11-26 14:56:00');
INSERT INTO `t_record` (`id`, `uid`, `position`, `temperature`, `cough`, `fever`, `time`) VALUES (113, 14, '安徽省安庆市怀宁县', 37.1, 0, 0, '2023-01-20 15:10:56');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `comment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` (`id`, `name`, `description`, `comment`) VALUES (1, 'admin', '管理员', '拥有至高无上的权限！');
INSERT INTO `t_role` (`id`, `name`, `description`, `comment`) VALUES (2, 'instructor', '辅导员', '拥有大部分的权限');
INSERT INTO `t_role` (`id`, `name`, `description`, `comment`) VALUES (3, 'student', '学生', '拥有少部分权限');
INSERT INTO `t_role` (`id`, `name`, `description`, `comment`) VALUES (4, 'faculty', '教职工', '拥有少部分权限');
COMMIT;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int NOT NULL COMMENT '角色ID',
  `permission_id` int NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (1, 1, 1);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (2, 1, 2);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (3, 1, 3);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (4, 1, 4);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (5, 1, 5);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (6, 1, 6);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (7, 1, 7);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (8, 1, 8);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (9, 1, 9);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (10, 1, 10);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (11, 1, 11);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (12, 2, 1);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (13, 2, 2);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (14, 2, 5);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (15, 2, 6);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (16, 2, 7);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (17, 2, 11);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (18, 3, 1);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (19, 3, 2);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (20, 3, 6);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (21, 3, 7);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (22, 3, 11);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (23, 4, 1);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (24, 4, 2);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (25, 4, 7);
INSERT INTO `t_role_permission` (`id`, `role_id`, `permission_id`) VALUES (26, 4, 11);
COMMIT;

-- ----------------------------
-- Table structure for t_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_upload`;
CREATE TABLE `t_upload` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型',
  `size` bigint DEFAULT NULL COMMENT '文件大小(KB)',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '下载链接',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否禁用链接',
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件md5',
  `upload_time` timestamp NULL DEFAULT NULL COMMENT '上传时间',
  `file_uuid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件UUID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_upload
-- ----------------------------
BEGIN;
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (1, 'aix.jpg', 'jpg', 61, 'http://localhost:8888/api/upload/af00f2e8feed4d19ade28dbbc1e33bc0', 0, 1, '7510540a6dc77138fd1f9ef955b354f8', '2022-11-11 22:31:29', 'af00f2e8feed4d19ade28dbbc1e33bc0');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (52, 'mysql_slow_query.log', 'log', 1, 'http://localhost:8888/api/upload/ab16422c0bf64820a207ae829abbd319', 0, 1, '78b9bb7405052e6ad078e51cd956fa97', '2022-11-11 22:50:53', 'ab16422c0bf64820a207ae829abbd319');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (53, 'player_config_experiment.ini', 'ini', 0, 'http://localhost:8888/api/upload/f71e884810cb47b7a0b1b56e6838a59f', 1, 1, '43daf56453a934d5d2a83f0bb5cb19aa', '2022-11-11 22:54:50', 'f71e884810cb47b7a0b1b56e6838a59f');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (54, 'player_config_experiment.ini', 'ini', 0, 'http://localhost:8888/api/upload/f71e884810cb47b7a0b1b56e6838a59f', 0, 1, '43daf56453a934d5d2a83f0bb5cb19aa', '2022-11-11 22:54:53', '6dce769708ff4cc4bc3ae62a6ee6ef0c');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (55, 'player_config_experiment.ini', 'ini', 0, 'http://localhost:8888/api/upload/f71e884810cb47b7a0b1b56e6838a59f', 1, 1, '43daf56453a934d5d2a83f0bb5cb19aa', '2022-11-11 22:54:54', 'b2624135b18540d9885cc663b8e262db');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (56, 'epacms.sql', 'sql', 61, 'http://localhost:8888/api/upload/8f8ae5f75e094864a0c5bfd955b643ed', 0, 1, '50bf3596905414360600308ef329383a', '2022-11-13 19:43:58', '8f8ae5f75e094864a0c5bfd955b643ed');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (64, 'guest.png', 'png', 6, 'http://localhost:8888/api/upload/7d68b921df844e7ab1345eee5d651da4', 0, 1, 'dd79342a1ae2f26be987ec94612d93fc', '2022-11-20 21:17:22', '7d68b921df844e7ab1345eee5d651da4');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (65, 'bg_dark.jpg', 'jpg', 197, 'http://localhost:8888/api/upload/9a24a62afb6c49ac9f5559521cd7c9e9', 0, 1, '83da17d5441391cc9bf2ea62a567926e', '2022-11-20 21:21:20', '9a24a62afb6c49ac9f5559521cd7c9e9');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (66, 'bg_dark.jpg', 'jpg', 197, 'http://localhost:8888/api/upload/9a24a62afb6c49ac9f5559521cd7c9e9', 0, 1, '83da17d5441391cc9bf2ea62a567926e', '2022-11-20 21:24:36', 'd6211562a6374ff0a030382f68d61b51');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (67, 'guest.png', 'png', 6, 'http://localhost:8888/api/upload/7d68b921df844e7ab1345eee5d651da4', 1, 1, 'dd79342a1ae2f26be987ec94612d93fc', '2022-11-20 21:33:15', 'a46f614acc694b9ebbbbd408cc8a0ff9');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (68, 'aix.jpg', 'jpg', 61, 'http://localhost:8888/api/upload/af00f2e8feed4d19ade28dbbc1e33bc0', 0, 1, '7510540a6dc77138fd1f9ef955b354f8', '2022-11-21 20:40:42', '4f463a32fbaa476e9e46de3a2f13d14a');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (69, '20110521204253-2136348486.jpg', 'jpg', 7, 'http://localhost:8888/api/upload/5894f837b6e247a580e122725ef1b212', 0, 1, 'a985498f1f006beda42b410617a122dc', '2022-11-26 09:41:27', '5894f837b6e247a580e122725ef1b212');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (70, 'a_job.sql', 'sql', 354, 'http://localhost:8888/api/upload/5ac1361059064b0da3e8e0097e3cca56', 0, 1, 'f1f7e83f026e0c23f1addda051cc4bd8', '2023-01-20 14:56:59', '5ac1361059064b0da3e8e0097e3cca56');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (71, '2018.jpg', 'jpg', 595, 'http://localhost:8888/api/upload/f262a7d0ea34439e8e9e8ff007a70d0d', 0, 1, '35fabc199d660d6880d61cf95d0104f6', '2023-01-20 14:59:58', 'f262a7d0ea34439e8e9e8ff007a70d0d');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (72, '2018.jpg', 'jpg', 595, 'http://localhost:8888/api/upload/f262a7d0ea34439e8e9e8ff007a70d0d', 0, 1, '35fabc199d660d6880d61cf95d0104f6', '2023-01-20 15:00:21', '295adef6049e47948736d94ff54e9807');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (73, '46961639572070.jpg', 'jpg', 60, 'http://localhost:8888/api/upload/2db68bc9563d41679ee75b69f40e4280', 0, 1, 'c7bae4c51727e16ea1de51f03dab5fbd', '2023-01-20 15:09:02', '2db68bc9563d41679ee75b69f40e4280');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (74, '祖国.jpg', 'jpg', 102, 'http://localhost:8888/api/upload/2336a226d9244dcd8b76c399fdbb4315', 0, 1, '3c387d77e3339e1918f33403f8c130ab', '2023-01-20 15:10:40', '2336a226d9244dcd8b76c399fdbb4315');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (75, '2开题报告（朱辉）.doc', 'doc', 42, 'http://localhost:8888/api/upload/3455f308d73a4d5eada9d231461d6df5', 0, 1, 'a1751312e00e0e62c503383a6a96c51f', '2023-01-20 15:12:20', '3455f308d73a4d5eada9d231461d6df5');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (76, 'gxiao.jpeg', 'jpeg', 56, 'http://localhost:8888/api/upload/b7cdf21b91b143b4bcab2e3a26b8013b', 0, 1, '332095181a8e78759ac750e4da6a3c31', '2023-01-28 20:02:25', 'b7cdf21b91b143b4bcab2e3a26b8013b');
INSERT INTO `t_upload` (`id`, `name`, `type`, `size`, `url`, `is_delete`, `enabled`, `md5`, `upload_time`, `file_uuid`) VALUES (77, 'gxiao.jpeg', 'jpeg', 56, 'http://localhost:8888/api/upload/b7cdf21b91b143b4bcab2e3a26b8013b', 0, 1, '332095181a8e78759ac750e4da6a3c31', '2023-01-28 20:02:40', '6b67159c514a4aa59c8b2bc3cc3efa43');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sno` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学号',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '真实姓名',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像url',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `age` int DEFAULT '18' COMMENT '年龄',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '男' COMMENT '性别',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '地址',
  `dormitory_num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '宿舍号',
  `nation` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '民族',
  `class_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '班级号',
  `academy` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '院系',
  `enable` smallint unsigned DEFAULT '1' COMMENT '是否禁用',
  `register_time` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `rid` int DEFAULT '3' COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `sno` (`sno`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (1, '2018052764', 'kevin', '凯文', 'http://localhost:8888/api/upload/f262a7d0ea34439e8e9e8ff007a70d0d', '$2a$10$tAmOtKup6sp9czY9YnBUGOYsbWfpVbOdMtBqBR7WIvHCC1khzom.O', 22, '女', NULL, '1538281872@qq.com', '湖南常德', NULL, '汉族', NULL, NULL, 1, '2022-11-20 12:04:22', 1);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (2, '2018052717', 'allen', '艾伦', 'http://localhost:8888/api/upload/2db68bc9563d41679ee75b69f40e4280', '$2a$10$Kj7LD1W2GcPEZAtZ5EwoDOfmRa2kAHRu5TbQrOKwqS/Ijgq0XvQIW', 22, '女', '13855623009', 'abc@qq.com', '湖北', '15-131', '苗族', '1806802', '土木工程学院', 1, '2022-08-01 19:29:42', 2);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (13, '2018052712', '关云长', '关羽', '', '$2a$10$ySoS8a14ZJMm8yfO0KvHxeTRAFuuFD6H6U6kAYxISH88XP1gWTL7K', 22, '男', '18711712894', '123', '山西省运城市盐湖区解州镇', '15-129', '汉族', '1806802', '体育学院', 1, '2022-03-19 15:02:52', 2);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (14, '2018052788', '赵子龙', '赵云', 'http://localhost:8888/api/upload/2336a226d9244dcd8b76c399fdbb4315', '$2a$10$IVtEpokDPC3d7TSeZ3dqNeh1xmgQsqOqWDHHBSdF.LfxVNqz1QhZq', 21, '男', '18711719382', 'zhaozilong@qq.com', '', '15-128', '维吾尔族', '1806802', '机械与电气工程学院', 1, '2022-03-24 15:41:01', 4);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (20, '1234567891', '周公瑾', '周瑜', 'http://localhost:8888/api/upload/b7cdf21b91b143b4bcab2e3a26b8013b', '$2a$10$R3.XGuooYOtsBcuDSxokp.NCivV8p5hEo0i37MmgKbp276pD.h08e', 22, '男', '13813928374', '12345@qq.com', '湖南岳阳', '15-120', '汉族', '1806802', '马克思主义学院', 1, '2022-01-01 12:23:06', 3);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (21, '2018052711', '荀文若', '荀彧', '', '79701e6722c1bccf868223a8e72ca76c', 22, '男', '13511137283', 'xunyu@qq.com', '河南许昌', '15-131', '汉族', '1806802', '人文学院', 1, '2022-04-04 17:05:52', 3);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (22, '2018052777', '蔡文姬', '蔡琰', '', 'f807e198ac2d1defb68196baf8c7e48e', 20, '女', '18711719372', 'caiwenji@qq.com', '河南杞县南五十里', '8-131', '汉族', '1806802', '艺术学院', 1, '2022-04-04 19:29:39', 3);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (23, '2018052776', '姜伯约', '姜维', '', 'bafb420813dca4a2c68d72c6306d7be1', 20, '男', '18711715327', 'jw@qq.com', '甘肃省天水市甘谷县', NULL, '汉族', '1806803', '体育学院', 1, '2022-04-04 21:11:10', 3);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (31, '2018052771', '莱布尼茨', '莱布尼茨', '', '56cded8d5ed38f6679e520c51ce42f78', 18, '男', '', 'xxx', '', NULL, NULL, '1806804', '马克思主义学院', 1, '2022-04-23 09:27:43', 3);
INSERT INTO `t_user` (`id`, `sno`, `username`, `real_name`, `avatar_url`, `password`, `age`, `sex`, `phone`, `email`, `address`, `dormitory_num`, `nation`, `class_num`, `academy`, `enable`, `register_time`, `rid`) VALUES (34, '2018052732', '马孟起', '马超', '', '951aa6788932f30edbf2ea33ce7ca6bf', 23, '男', '155728302938', '152@qq.com', '陕西兴平', NULL, '羌族', '1806801', '体育学院', 1, '2022-05-07 22:08:30', 3);
COMMIT;

-- ----------------------------
-- Table structure for t_user_grade
-- ----------------------------
DROP TABLE IF EXISTS `t_user_grade`;
CREATE TABLE `t_user_grade` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int unsigned DEFAULT NULL COMMENT '用户id',
  `class_id` int unsigned DEFAULT NULL COMMENT '班级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_user_grade
-- ----------------------------
BEGIN;
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (1, 20, 1);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (2, 21, 2);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (3, 35, 2);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (4, 22, 3);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (5, 23, 3);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (6, 31, 4);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (7, 34, 4);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (8, 2, 1);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (9, 2, 2);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (10, 13, 3);
INSERT INTO `t_user_grade` (`id`, `user_id`, `class_id`) VALUES (11, 13, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
