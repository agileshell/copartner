var industryDomainData = [{
	"value": 1,
	"text": "互联网"
}, {
	"value": 2,
	"text": "移动互联网"
}, {
	"value": 3,
	"text": "电子商务"
}, {
	"value": 4,
	"text": "社交"
}, {
	"value": 5,
	"text": "游戏"
}, {
	"value": 6,
	"text": "云计算"
}, {
	"value": 7,
	"text": "IT服务"
}, {
	"value": 8,
	"text": "软件/工具"
}, {
	"value": 9,
	"text": "软硬件"
}, {
	"value": 10,
	"text": "数码电子"
}, {
	"value": 11,
	"text": "其它"
}];

var teamSizeData = [{
	"value": 1,
	"text": "少于50人"
}, {
	"value": 2,
	"text": "50-99人"
}, {
	"value": 3,
	"text": "100-499人"
}, {
	"value": 4,
	"text": "500-999人"
}, {
	"value": 5,
	"text": "1000人以上"
}];

var financingPhaseData = [{
	"value": 1,
	"text": "天使投资"
}, {
	"value": 2,
	"text": "风险投资"
}, {
	"value": 3,
	"text": "私募股权投资"
}, {
	"value": 4,
	"text": "首次公开募股"
}];

var startupRoleData = [{
	"value": 1,
	"text": "产品"
}, {
	"value": 2,
	"text": "技术"
}, {
	"value": 3,
	"text": "运营"
}, {
	"value": 4,
	"text": "营销"
}, {
	"value": 5,
	"text": "设计"
}, {
	"value": 6,
	"text": "其它"
}];

var roleData = [{
	"value": 1,
	"text": "创业者"
}, {
	"value": 2,
	"text": "投资人"
}, {
	"value": 3,
	"text": "导师"
}];

var professionData = [{
	"value": 1,
	"text": "学术型"
},{
	"value": 2,
	"text": "实业型"
}];

var startupStatusData = [{
	"value": 1,
	"text": "有创业意愿"
}, {
	"value": 2,
	"text": "有创业想法"
}, {
	"value": 3,
	"text": "全职创业中"
}, {
	"value": 4,
	"text": "兼职创业中"
}, {
	"value": 5,
	"text": "投资人"
}, {
	"value": 6,
	"text": "媒体人"
}];

var genderData = [{
	"value": "M",
	"text": "男"
}, {
	"value": "F",
	"text": "女"
}];

var ageData = [{
	"value": "<25",
	"text": "<25"
}, {
	"value": "25-30",
	"text": "25-30"
}, {
	"value": "31-35",
	"text": "31-35"
}, {
	"value": "36-40",
	"text": "36-40"
}, {
	"value": ">40",
	"text": ">40"
}];

var projectPhaseData = [{
	"value": 1,
	"text": "原型构建阶段"
}, {
	"value": 2,
	"text": "Beta测试阶段"
}, {
	"value": 3,
	"text": "已运营"
}, {
	"value": 4,
	"text": "已收益"
}];

var locationData = [
{"value": 1, "text": "北京", "children": [{"value":35, "text": "北京"}]},
{"value": 2, "text": "上海", "children": [{"value":36, "text": "上海"}]},
{"value": 3, "text": "天津", "children": [{"value":37, "text": "天津"}]},
{"value": 4, "text": "重庆", "children": [{"value":38, "text": "重庆"}]},
{"value": 5, "text": "辽宁", "children": [{"value":39, "text": "沈阳"},{"value":40, "text": "大连"},{"value":41, "text": "鞍山"},{"value":42, "text": "抚顺"},{"value":43, "text": "本溪"},{"value":44, "text": "丹东"},{"value":45, "text": "锦州"},{"value":46, "text": "营口"},{"value":47, "text": "阜新"},{"value":48, "text": "辽阳"},{"value":49, "text": "盘锦"},{"value":50, "text": "铁岭"},{"value":51, "text": "朝阳"},{"value":52, "text": "葫芦岛"}]},
{"value": 6, "text": "吉林", "children": [{"value":53, "text": "长春"},{"value":54, "text": "吉林"},{"value":55, "text": "四平"},{"value":56, "text": "辽源"},{"value":57, "text": "通化"},{"value":58, "text": "白山"},{"value":59, "text": "松原"},{"value":60, "text": "白城"},{"value":61, "text": "延边"}]},
{"value": 7, "text": "黑龙江", "children": [{"value":62, "text": "哈尔滨"},{"value":63, "text": "齐齐哈尔"},{"value":64, "text": "鸡西"},{"value":65, "text": "鹤岗"},{"value":66, "text": "双鸭山"},{"value":67, "text": "大庆"},{"value":68, "text": "伊春"},{"value":69, "text": "佳木斯"},{"value":70, "text": "七台河"},{"value":71, "text": "牡丹江"},{"value":72, "text": "黑河"},{"value":73, "text": "绥化"},{"value":74, "text": "大兴安岭"}]},
{"value": 8, "text": "河北", "children": [{"value":75, "text": "石家庄"},{"value":76, "text": "唐山"},{"value":77, "text": "秦皇岛"},{"value":78, "text": "邯郸"},{"value":79, "text": "邢台"},{"value":80, "text": "保定"},{"value":81, "text": "张家口"},{"value":82, "text": "承德"},{"value":83, "text": "沧州"},{"value":84, "text": "廊坊"},{"value":85, "text": "衡水"}]},
{"value": 9, "text": "山西", "children": [{"value":86, "text": "太原"},{"value":87, "text": "大同"},{"value":88, "text": "阳泉"},{"value":89, "text": "长治"},{"value":90, "text": "晋城"},{"value":91, "text": "朔州"},{"value":92, "text": "晋中"},{"value":93, "text": "运城"},{"value":94, "text": "忻州"},{"value":95, "text": "临汾"},{"value":96, "text": "吕梁"}]},
{"value": 10, "text": "陕西", "children": [{"value":97, "text": "西安市"},{"value":98, "text": "铜川市"},{"value":99, "text": "宝鸡市"},{"value":100, "text": "咸阳市"},{"value":101, "text": "渭南市"},{"value":102, "text": "延安市"},{"value":103, "text": "汉中市"},{"value":104, "text": "榆林市"},{"value":105, "text": "安康市"},{"value":106, "text": "商洛市"}]},
{"value": 11, "text": "甘肃", "children": [{"value":107, "text": "兰州"},{"value":108, "text": "嘉峪关"},{"value":109, "text": "金昌"},{"value":110, "text": "白银"},{"value":111, "text": "天水"},{"value":112, "text": "武威"},{"value":113, "text": "张掖"},{"value":114, "text": "平凉"},{"value":115, "text": "酒泉"},{"value":116, "text": "庆阳"},{"value":117, "text": "定西"},{"value":118, "text": "陇南"},{"value":119, "text": "临夏"},{"value":120, "text": "甘南"}]},
{"value": 12, "text": "青海", "children": [{"value":121, "text": "西宁"},{"value":122, "text": "海东"},{"value":123, "text": "海北"},{"value":124, "text": "黄南"},{"value":125, "text": "海南"},{"value":126, "text": "果洛"},{"value":127, "text": "玉树"},{"value":128, "text": "海西"}]},
{"value": 13, "text": "山东", "children": [{"value":129, "text": "济南"},{"value":130, "text": "青岛"},{"value":131, "text": "淄博"},{"value":132, "text": "枣庄"},{"value":133, "text": "东营"},{"value":134, "text": "烟台"},{"value":135, "text": "潍坊"},{"value":136, "text": "济宁"},{"value":137, "text": "泰安"},{"value":138, "text": "威海"},{"value":139, "text": "日照"},{"value":140, "text": "莱芜"},{"value":141, "text": "临沂"},{"value":142, "text": "德州"},{"value":143, "text": "聊城"},{"value":144, "text": "滨州"},{"value":145, "text": "菏泽"}]},
{"value": 14, "text": "安徽", "children": [{"value":146, "text": "合肥"},{"value":147, "text": "芜湖"},{"value":148, "text": "蚌埠"},{"value":149, "text": "淮南"},{"value":150, "text": "马鞍山"},{"value":151, "text": "淮北"},{"value":152, "text": "铜陵"},{"value":153, "text": "安庆"},{"value":154, "text": "黄山"},{"value":155, "text": "滁州"},{"value":156, "text": "阜阳"},{"value":157, "text": "宿州"},{"value":158, "text": "巢湖"},{"value":159, "text": "六安"},{"value":160, "text": "亳州"},{"value":161, "text": "池州"},{"value":162, "text": "宣城"}]},
{"value": 15, "text": "江苏", "children": [{"value":163, "text": "南京"},{"value":164, "text": "无锡"},{"value":165, "text": "徐州"},{"value":166, "text": "常州"},{"value":167, "text": "苏州"},{"value":168, "text": "南通"},{"value":169, "text": "连云港"},{"value":170, "text": "淮安"},{"value":171, "text": "盐城"},{"value":172, "text": "扬州"},{"value":173, "text": "镇江"},{"value":174, "text": "泰州"},{"value":175, "text": "宿迁"}]},
{"value": 16, "text": "浙江", "children": [{"value":176, "text": "杭州"},{"value":177, "text": "宁波"},{"value":178, "text": "温州"},{"value":179, "text": "嘉兴"},{"value":180, "text": "湖州"},{"value":181, "text": "绍兴"},{"value":182, "text": "金华"},{"value":183, "text": "衢州"},{"value":184, "text": "舟山"},{"value":185, "text": "台州"},{"value":186, "text": "丽水"}]},
{"value": 17, "text": "河南", "children": [{"value":187, "text": "郑州"},{"value":188, "text": "开封"},{"value":189, "text": "洛阳"},{"value":190, "text": "平顶山"},{"value":191, "text": "安阳"},{"value":192, "text": "鹤壁"},{"value":193, "text": "新乡"},{"value":194, "text": "焦作"},{"value":195, "text": "濮阳"},{"value":196, "text": "许昌"},{"value":197, "text": "漯河"},{"value":198, "text": "三门峡"},{"value":199, "text": "南阳"},{"value":200, "text": "商丘"},{"value":201, "text": "信阳"},{"value":202, "text": "周口"},{"value":203, "text": "驻马店"}]},
{"value": 18, "text": "湖北", "children": [{"value":204, "text": "武汉"},{"value":205, "text": "黄石"},{"value":206, "text": "十堰"},{"value":207, "text": "宜昌"},{"value":208, "text": "襄樊"},{"value":209, "text": "鄂州"},{"value":210, "text": "荆门"},{"value":211, "text": "孝感"},{"value":212, "text": "荆州"},{"value":213, "text": "黄冈"},{"value":214, "text": "咸宁"},{"value":215, "text": "随州"},{"value":216, "text": "恩施"}]},
{"value": 19, "text": "湖南", "children": [{"value":217, "text": "长沙"},{"value":218, "text": "株洲"},{"value":219, "text": "湘潭"},{"value":220, "text": "衡阳"},{"value":221, "text": "邵阳"},{"value":222, "text": "岳阳"},{"value":223, "text": "常德"},{"value":224, "text": "张家界"},{"value":225, "text": "益阳"},{"value":226, "text": "郴州"},{"value":227, "text": "永州"},{"value":228, "text": "怀化"},{"value":229, "text": "娄底"},{"value":230, "text": "湘西"}]},
{"value": 20, "text": "江西", "children": [{"value":231, "text": "南昌"},{"value":232, "text": "景德镇"},{"value":233, "text": "萍乡"},{"value":234, "text": "九江"},{"value":235, "text": "新余"},{"value":236, "text": "鹰潭"},{"value":237, "text": "赣州"},{"value":238, "text": "吉安"},{"value":239, "text": "宜春"},{"value":240, "text": "抚州"},{"value":241, "text": "上饶"}]},
{"value": 21, "text": "福建", "children": [{"value":242, "text": "福州"},{"value":243, "text": "厦门"},{"value":244, "text": "莆田"},{"value":245, "text": "三明"},{"value":246, "text": "泉州"},{"value":247, "text": "漳州"},{"value":248, "text": "南平"},{"value":249, "text": "龙岩"},{"value":250, "text": "宁德"}]},
{"value": 22, "text": "云南", "children": [{"value":251, "text": "昆明"},{"value":252, "text": "曲靖"},{"value":253, "text": "玉溪"},{"value":254, "text": "保山"},{"value":255, "text": "昭通"},{"value":256, "text": "丽江"},{"value":257, "text": "思茅"},{"value":258, "text": "临沧"},{"value":259, "text": "楚雄"},{"value":260, "text": "红河"},{"value":261, "text": "文山"},{"value":262, "text": "西双版纳"},{"value":263, "text": "大理"},{"value":264, "text": "德宏"},{"value":265, "text": "怒江"},{"value":266, "text": "迪庆"}]},
{"value": 23, "text": "海南", "children": [{"value":267, "text": "海口"},{"value":268, "text": "三亚"}]},
{"value": 24, "text": "四川", "children": [{"value":269, "text": "成都"},{"value":270, "text": "自贡"},{"value":271, "text": "攀枝花"},{"value":272, "text": "泸州"},{"value":273, "text": "德阳"},{"value":274, "text": "绵阳"},{"value":275, "text": "广元"},{"value":276, "text": "遂宁"},{"value":277, "text": "内江"},{"value":278, "text": "乐山"},{"value":279, "text": "南充"},{"value":280, "text": "眉山"},{"value":281, "text": "宜宾"},{"value":282, "text": "广安"},{"value":283, "text": "达州"},{"value":284, "text": "雅安"},{"value":285, "text": "巴中"},{"value":286, "text": "资阳"},{"value":287, "text": "阿坝"},{"value":288, "text": "甘孜"},{"value":289, "text": "凉山"}]},
{"value": 25, "text": "贵州", "children": [{"value":290, "text": "贵阳"},{"value":291, "text": "六盘水"},{"value":292, "text": "遵义"},{"value":293, "text": "安顺"},{"value":294, "text": "铜仁"},{"value":295, "text": "黔西南"},{"value":296, "text": "毕节"},{"value":297, "text": "黔东南"},{"value":298, "text": "黔南"}]},
{"value": 26, "text": "广东", "children": [{"value":299, "text": "广州"},{"value":300, "text": "韶关"},{"value":301, "text": "深圳"},{"value":302, "text": "珠海"},{"value":303, "text": "汕头"},{"value":304, "text": "佛山"},{"value":305, "text": "江门"},{"value":306, "text": "湛江"},{"value":307, "text": "茂名"},{"value":308, "text": "肇庆"},{"value":309, "text": "惠州"},{"value":310, "text": "梅州"},{"value":311, "text": "汕尾"},{"value":312, "text": "河源"},{"value":313, "text": "阳江"},{"value":314, "text": "清远"},{"value":315, "text": "东莞"},{"value":316, "text": "中山"},{"value":317, "text": "潮州"},{"value":318, "text": "揭阳"},{"value":319, "text": "云浮"}]},
{"value": 27, "text": "内蒙古", "children": [{"value":320, "text": "呼和浩特"},{"value":321, "text": "包头"},{"value":322, "text": "乌海"},{"value":323, "text": "赤峰"},{"value":324, "text": "通辽"},{"value":325, "text": "鄂尔多斯"},{"value":326, "text": "呼伦贝尔"},{"value":327, "text": "巴彦淖尔"},{"value":328, "text": "乌兰察布"},{"value":329, "text": "兴安盟"},{"value":330, "text": "锡林郭勒盟"},{"value":331, "text": "阿拉善盟"}]},
{"value": 28, "text": "新疆", "children": [{"value":332, "text": "乌鲁木齐"},{"value":333, "text": "克拉玛依"},{"value":334, "text": "吐鲁番"},{"value":335, "text": "哈密"},{"value":336, "text": "昌吉"},{"value":337, "text": "博尔塔拉"},{"value":338, "text": "巴音郭楞"},{"value":339, "text": "阿克苏"},{"value":340, "text": "克州"},{"value":341, "text": "喀什"},{"value":342, "text": "和田"},{"value":343, "text": "伊犁"},{"value":344, "text": "塔城"},{"value":345, "text": "阿勒泰"}]},
{"value": 29, "text": "广西", "children": [{"value":346, "text": "南宁"},{"value":347, "text": "柳州"},{"value":348, "text": "桂林"},{"value":349, "text": "梧州"},{"value":350, "text": "北海"},{"value":351, "text": "防城港"},{"value":352, "text": "钦州"},{"value":353, "text": "贵港"},{"value":354, "text": "玉林"},{"value":355, "text": "百色"},{"value":356, "text": "贺州"},{"value":357, "text": "河池"},{"value":358, "text": "来宾"},{"value":359, "text": "崇左"}]},
{"value": 30, "text": "西藏", "children": [{"value":360, "text": "拉萨"},{"value":361, "text": "昌都"},{"value":362, "text": "山南"},{"value":363, "text": "日喀则"},{"value":364, "text": "那曲"},{"value":365, "text": "阿里"},{"value":366, "text": "林芝"}]},
{"value": 31, "text": "宁夏", "children": [{"value":367, "text": "银川"},{"value":368, "text": "石嘴山"},{"value":369, "text": "吴忠"},{"value":370, "text": "固原"},{"value":371, "text": "中卫"}]},
{"value": 32, "text": "香港", "children": [{"value":372, "text": "香港"}]},
{"value": 33, "text": "澳门", "children": [{"value":373, "text": "澳门"}]},
{"value": 34, "text": "台湾", "children": [{"value":374, "text": "台湾"}]}
];