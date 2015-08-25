//现场服务类别
var SCENE_IMAGE_BASE = "resources/xcfwImages/";
var CON_SCENE_TYPE = [
                      {name:"折椅(Folding Chair)",image:"zheyi.png",info:["提前10天预订单价(RMB)：40.00","现场租赁单价(RMB)：60.00","押金（RMB）：100.00"]},
                      {name:"汽巴椅(Chair)",image:"qibayi.png",info:["提前10天预订单价(RMB)：80.00","现场租赁单价（RMB）：120.00","押金（RMB）：200.00"]},
                      {name:"办公椅(Office Chair)",image:"bangongyi.png",info:["提前10天预订单价(RMB)：120.00","现场租赁单价（RMB）：200.00","押金（RMB）：300.00"]},
                      {name:"黒皮椅(Leather Chair)",image:"heipiyi.png",info:["提前10天预订单价(RMB)：100.00","现场租赁单价（RMB）：120.00","押金（RMB）：200.00"]},
                      {name:"单人沙发(Sofa)",image:"danrenshafa.png",info:["提前10天预订单价(RMB)：300.00","现场租赁单价（RMB）：350.00","押金（RMB）：500.00"]},
                      {name:"双人沙发(Sofa)",image:"shuangrenshafa.png",info:["提前10天预订单价(RMB)：500.00","现场租赁单价（RMB）：600.00","押金（RMB）：800.00"]},
                      {name:"咖啡桌(Coffee Table)",image:"kafeizhuo.png",info:["提前10天预订单价(RMB)：40.00","现场租赁单价（RMB）：60.00","押金（RMB）：100.00"]},
                      {name:"展示台(Display Cube)",image:"zhanshitai.png",info:["提前10天预订单价(RMB)：100.00","现场租赁单价（RMB）：150.00","押金（RMB）：200.00"]},
                      {name:"询问台(Information)",image:"zixuntai.png",info:["提前10天预订单价(RMB)：150.00","现场租赁单价（RMB）：200.00","押金（RMB）：300.00"]},
                      {name:"方桌(Square Table)",image:"fangzhuo.png",info:["提前10天预订单价(RMB)：100.00","现场租赁单价（RMB）：150.00","押金（RMB）：200.00"]},
                      {name:"长方桌(Rectangular Table)",image:"changfangzhuo.png",info:["提前10天预订单价(RMB)：300.00","现场租赁单价（RMB）：350.00","押金（RMB）：500.00"]},
                      {name:"高饰柜(Tall Showcase)",image:"gaoshigui.png",info:["提前10天预订单价(RMB)：500.00","现场租赁单价（RMB）：600.00","押金（RMB）：800.00"]},
                      {name:"圆桌(Round Table)",image:"yuanzhuo.png",info:["提前10天预订单价(RMB)：150.00","现场租赁单价（RMB）：200.00","押金（RMB）：300.00"]},
                      {name:"玻璃圆桌(Glass Table)",image:"boliyuanzhuo.png",info:["提前10天预订单价(RMB)：150.00","现场租赁单价（RMB）：200.00","押金（RMB）：200.00"]},
                      {name:"高圆桌(High Round Table)",image:"gaoyuanzhuo.png",info:["提前10天预订单价(RMB)：200.00","现场租赁单价（RMB）：250.00","押金（RMB）：300.00"]},
                      {name:"锁柜(Lockable Cupboard)",image:"suogui.png",info:["提前10天预订单价(RMB)：320.00","现场租赁单价（RMB）：400.00","押金（RMB）：500.00"]},
                      {name:"低饰柜(Table Showcase)",image:"dishigui.png",info:["提前10天预订单价(RMB)：400.00","现场租赁单价（RMB）：500.00","押金（RMB）：600.00"]},
                      {name:"层板架(Shelf Rack)",image:"cengbanjia.png",info:["提前10天预订单价(RMB)：250.00","现场租赁单价（RMB）：350.00","押金（RMB）：500.00"]},
                      {name:"斜(平)层板(Shelf)",image:"xiebanceng.png",info:["提前10天预订单价(RMB)：50.00","现场租赁单价（RMB）：80.00","押金（RMB）：100.00"]},
                      {name:"等离子电视(Plasma)",image:"denglizidianshi.png",info:["提前10天预订单价(RMB)：800.00","现场租赁单价（RMB）：1200.00","押金（RMB）：1500.00"]}
                      ];

//货运物流
var CON_TRANS_TYPE = [
                      {name:"现场叉车",info:["单位：3吨叉车","单价（元）：100元/小时","备注：不少于2小时"]},
                      {name:"汽车吊",info:["单位：3吨叉车","单价（元）：100元/小时","备注：不少于2小时"]}
                      ];

//招商引荐单位
var CON_REC_SEL = [
                      {name:"国家贸促会贸推中心"},
                      {name:"江苏省贸促会"},
                      {name:"中国外运长航集团"},
                      {name:"江苏省经信委"},
                      {name:"江苏省交通厅"},
                      {name:"江苏省货代协会"},
                      {name:"连云港市东海县"},
                      {name:"连云港市灌云县"},
                      {name:"连云港市灌南县"},
                      {name:"连云港市赣榆区"},
                      {name:"连云港市海州区"},
                      {name:"连云港市连云区"},
                      {name:"连云港市开发区"},
                      {name:"连云港市徐圩新区"},
                      {name:"连云港市发改委"},
                      {name:"连云港市经信委"},
                      {name:"连云港市交通局"},
                      {name:"连云港市商务局"},
                      {name:"连云港市港口管理局"},
                      {name:"连云港市台办"},
                      {name:"连云港市外侨办"},
                      {name:"连云港市侨联"},
                      {name:"连云港市贸促会"},
                      {name:"连云港市邮政管理局"},
                      {name:"连云港市港口集团"},
                      {name:"连云港市货代协会"},
                      {name:"江苏新国际会展集团有限公司"},
                      {name:"中国物流与采购联合会"}
                      ];