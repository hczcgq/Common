
public class GeneralExaminationDao extends AbstractDao<GeneralExamination, String> {

    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'GENERAL_EXAMINATION' (" + //
                "'EXAMINATION_NO' TEXT PRIMARY KEY NOT NULL ," + // 0: examinationNo
                "'IDCARD' TEXT NOT NULL ," +  //身份证
                "'ZRYS' TEXT," + //责任医师
                "'TJRQ' TEXT," +  //体检日期
                "'ZZ' TEXT," + //症状
                "'YBQK_TW' TEXT," +//一般情况-体温
                "'YBQK_MB' TEXT," + //一般情况-脉搏
                "'YBQK_HXPL' TEXT," +//一般情况-呼吸频率
                "'YBQK_ZCXY' TEXT," + //一般情况-左侧血压
                "'YBQK_YCXY' TEXT," + //一般情况-右侧血压
                "'YBQK_SG' TEXT," + //一般情况-身高
                "'YBQK_TZ' TEXT," +  //一般情况-体重
                "'YBQK_BMI' TEXT," + //一般情况-BMI
                "'YBQK_YW' TEXT," + //一般情况-腰围
                "'YBQK_JKZK' TEXT," + //一般情况-老年人健康状况自我评估
                "'YBQK_SHZLNL' TEXT," +//一般情况-老年人生活自理能力自我评估
                "'YBQK_RZGN' TEXT," +  //一般情况-认真功能
                "'YBQK_ZLJCZF' TEXT," +  //一般情况-简易智力状态检查-总分
                "'YBQK_QGZT' TEXT," + //一般情况-老年人情感状态
                "'YBQK_YYZTJCZF' TEXT," +  //一般情况-简易抑郁状态检查-总分
                "'SHFS_DLPL' TEXT," +  //生活方式-锻炼频率
                "'SHFS_MCDLSJ' TEXT," +//生活方式-每次锻炼时间
                "'SHFS_JCDLSJ' TEXT," + //生活方式-检查锻炼
                "'SHFS_DLFS' TEXT," + //生活方式-锻炼方式
                "'SHFS_YSXG' TEXT," + //生活方式-饮食习惯
                "'SHFS_XYZK' TEXT," + //生活方式-吸烟状况
                "'SHFS_RXYL' TEXT," + //生活方式-日吸烟量
                "'SHFS_KSXYNL' TEXT," +//生活方式-开始吸烟年龄
                "'SHFS_JYNL' TEXT," + //生活方式-戒烟年龄
                "'SHFS_YJPL' TEXT," + //生活方式-饮酒频率
                "'SHFS_RYJL' TEXT," + //生活方式-日饮酒量
                "'SHFS_SFJY' INTEGER," + //生活方式-是否戒烟
                "'SHFS_KSYJNL' TEXT," + //生活方式-开始烟酒年龄
                "'SHFS_JJNL' TEXT," + //生活方式-戒酒年龄
                "'SHFS_SFZJ' INTEGER," +//生活方式-近一年是否戒酒
                "'SHFS_YJZL' TEXT," + //生活方式-饮酒种类
                "'SHFS_ZYBWHYSJCS' INTEGER," + //生活方式-职业病危害因素接触史
                "'SHFS_GZ' TEXT," +//生活方式-工种
                "'SHFS_CYSJ' TEXT," + //生活方式-从业时间
                "'SHFS_DWZL' TEXT," + //生活方式-毒物种类
                "'ZQGN_KC' TEXT," + //脏器功能-口唇
                "'ZQGN_SFCL' INTEGER," + //脏器功能-是否齿列
                "'ZQGN_CLMS' TEXT," +//脏器功能-齿列描述
                "'ZQGN_YB' TEXT," + //脏器功能-咽部
                "'ZQGN_LYSL' TEXT," + //脏器功能-裸眼视力
                "'ZQGN_JZSL' TEXT," +//脏器功能-矫正视力
                "'ZQGN_TL' TEXT," + //脏器功能-听力
                "'ZQGN_YDNL' TEXT," + //脏器功能-运动能力
                "'CT_SFYDYC' INTEGER," + //查体-是否眼底异常
                "'CT_YDYCMS' TEXT," + //查体-眼底异常描述
                "'CT_PF' TEXT," + //查体-皮肤
                "'CT_GM' TEXT," + //查体-巩膜
                "'CT_LBJ' TEXT," + // //查体-淋巴结
                "'CT_SFTZX' INTEGER," + //查体-是否桶装胸
                "'CT_SFHXY' INTEGER," + //查体-是否呼吸音
                "'CT_HXYMS' TEXT," +//查体-呼吸音描述
                "'CT_LY' TEXT," + //查体-罗音
                "'CT_RATE' TEXT," + //查体-心率
                "'CT_RHYTHM' TEXT," + //查体-心律
                "'CT_SFZY' INTEGER," + //查体-是否杂音
                "'CT_ZYMS' TEXT," + //查体-杂音描述
                "'CT_SFYT' INTEGER," + //查体-是否压痛
                "'CT_YTMS' TEXT," + //查体-压痛描述
                "'CT_SFBK' INTEGER," + //查体-是否包块
                "'CT_BKMS' TEXT," + //查体-包块描述
                "'CT_SFGD' INTEGER," + //查体-是否肝大
                "'CT_GDMS' TEXT," + //查体-肝大描述
                "'CT_SFPD' INTEGER," + //查体-是否脾大
                "'CT_PDMS' TEXT," +//查体-脾大描述
                "'CT_SFYDXZY' INTEGER," + //查体-是否移动性浊音
                "'CT_YDXZYMS' TEXT," + //查体-移动性浊音描述
                "'CT_XZSZ' TEXT," +//查体-下肢水肿
                "'CT_ZBDMBD' TEXT," + //查体-足背动脉搏动
                "'CT_KMZZ' TEXT," + //查体-肛门指诊
                "'CT_RX' TEXT," + //查体-乳腺
                "'CT_WY' TEXT," + //查体-外阴
                "'CT_YD' TEXT," + //查体-阴道
                "'CT_GT' TEXT," + //查体-宫体
                "'CT_' TEXT," + //查体-宫颈？_
                "'CT_FJ' TEXT," + // //查体-附件
                "'CT_QT' TEXT," + ////查体-其他
                "'FZJC_XHDB' TEXT," + //辅助检查-血红蛋白
                "'FZJC_BXB' TEXT," + //辅助检查-白雪胞
                "'FZJC_XXB' TEXT," + //辅助检查-血小板
                "'FZJC_XCGQT' TEXT," +//辅助检查-血常规其他
                "'FZJC_NDB' TEXT," + //辅助检查-尿蛋白
                "'FZJC_NDBXZ' TEXT," + //辅助检查-尿蛋白性质
                "'FZJC_NT' TEXT," +//辅助检查-尿糖
                "'FZJC_NTXZ' TEXT," + //辅助检查-尿糖性质
                "'FZJC_NTTXZ' TEXT," + //辅助检查-尿酮体性质
                "'FZJC_NQXXZ' TEXT," + //辅助检查-尿潜血性质
                "'FZJC_NBZ' TEXT," + //辅助检查-尿比重
                "'FZJC_NYSJD' TEXT," +//辅助检查-尿液酸碱度
                "'FZJC_NCGQT' TEXT," +//辅助检查-尿常规其他
                "'FZJC_KFXT' TEXT," +//辅助检查-空腹血糖
                "'FZJC_SFXDTZC' INTEGER," +//辅助检查-是否心电图正常
                "'FZJC_XDTYCXX' TEXT," + //辅助检查-心电图异常信息？
                "'FZJC_NWLBDB' TEXT," + //辅助检查-尿微量白蛋白
                "'FZJC_SFDBQX' INTEGER," + //辅助检查-是否大便潜血
                "'FZJC_THXHDB' TEXT," +//辅助检查-糖化血红蛋白
                "'FZJC_BMKY' TEXT," + //辅助检查-乙型肝炎表面抗原
                "'FZJC_HXKT' TEXT," + //辅助检查-乙型肝炎核心抗体  
                "'FZJC_EKY' TEXT," + //辅助检查-乙型肝炎e抗原
                "'FZJC_EKT' TEXT," + //辅助检查-乙型肝炎e抗体
                "'FZJC_XQGBZAM' TEXT," + //辅助检查-血清谷丙转氨酶
                "'FZJC_XQGCZAM' TEXT," +//辅助检查-血清谷草转氨酶
                "'FZJC_BDB' TEXT," + //辅助检查-白蛋白
                "'FZJC_ZDHS' TEXT," + //辅助检查-总胆红素
                "'FZJC_JHDHS' TEXT," +//辅助检查-结合胆红素
                "'FZJC_XQJG' TEXT," + //辅助检查-血清肌酐
                "'FZJC_XNSD' TEXT," +//辅助检查-血尿素氮
                "'FZJC_XJND' TEXT," + //辅助检查-血钾浓度
                "'FZJC_XNND' TEXT," + //辅助检查-血钠浓度
                "'FZJC_ZDGC' TEXT," + //辅助检查-总胆固醇
                "'FZJC_DMDZDB' TEXT," + //辅助检查-低密度脂蛋白
                "'FZJC_GYSZ' TEXT," +//辅助检查-甘油三酯
                "'FZJC_GMDZDB' TEXT," + //辅助检查-高密度脂蛋白
                "'FZJC_APKY' TEXT," + //辅助检查-癌胚抗原
                "'FZJC_SFXBXXPZC' INTEGER," + //辅助检查-是否胸部x片正常
                "'FZJC_XBXXPYCMS' TEXT," + //辅助检查-胸部x片异常描述
                "'FZJC_SFBCZC' INTEGER," + //辅助检查-是否B超正常
                "'FZJC_BCYCMS' TEXT," + //辅助检查-B超异常描述
                "'FZJC_SFGJPZC' INTEGER," + //辅助检查-是否宫颈涂片正常
                "'FZJC_GJPYCMS' TEXT," +//辅助检查-宫颈涂片异常描述
                "'FZJC_QT' TEXT," + //辅助检查-其他
                "'ZYTZBS_PHZ' TEXT," + //中医体质辨识-平和质
                "'ZYTZBS_QXZ' TEXT," + //中医体质辨识-气虚质
                "'ZYTZBS_YANGXZ' TEXT," + //中医体质辨识-阳虚质
                "'ZYTZBS_YINXZ' TEXT," + //中医体质辨识-阴虚质
                "'ZYTZBS_TSZ' TEXT," + //中医体质辨识-痰湿质
                "'ZYTZBS_SRZ' TEXT," + //中医体质辨识-湿热质
                "'ZYTZBS_XYZ' TEXT," + //中医体质辨识-血瘀质
                "'ZYTZBS_QYZ' TEXT," + ///中医体质辨识-气郁质
                "'ZYTZBS_TBZ' TEXT," + //中医体质辨识-特秉质
                "'JKWT_NXGJB' TEXT," +//现存主要健康问题-脑血管疾病
                "'JKWT_SZJB' TEXT," + //现存主要健康问题-肾脏疾病
                "'JKWT_XZJB' TEXT," + //现存主要健康问题-心脏疾病
                "'JKWT_XGJB' TEXT," + //现存主要健康问题-血管疾病
                "'JKWT_YBJB' TEXT," + //现存主要健康问题-眼部疾病
                "'JKWT_SFSJXTJB' INTEGER," + //现存主要健康问题-是否神经系统疾病
                "'JKWT_SJXTJBMS' TEXT," +//现存主要健康问题-神经系统疾病描述
                "'JKWT_SFQTXTJB' INTEGER," + //现存主要健康问题-是否其他系统疾病
                "'JKWT_QTXTJBMS' TEXT," + //现存主要健康问题-其他系统疾病描述
                "'ZYYLQK' TEXT," + //住院医疗情况
                "'ZYYYQK' TEXT," +//主要用药情况
                "'FMYGHFYJZS' TEXT," + //非免疫规划防御接种史
                "'JKPJ_SFTJYC' INTEGER," + //健康评估-是否体检异常
                "'JKPJ_YC1' TEXT," + //健康评估-异常1
                "'JKPJ_YC2' TEXT," + //健康评估-异常2
                "'JKPJ_YC3' TEXT," +//健康评估-异常2
                "'JKZD' TEXT," + //健康指导
                "'WXYSKZ' TEXT," + //危险因素控制
                "'CREATE_TIME' TEXT," + //创建日期
                "'UPDATE_TIME' TEXT);"); //更新日期



     db.execSQL("CREATE TABLE " + constraint + "'EXAMINATION_INFO' (" + //
                "'EXAMINATION_NO' TEXT PRIMARY KEY NOT NULL ," + // 快速体检编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'ROUTINE_CHECKUPS' TEXT," + // 2: routineCheckups
                "'BLOOD_PRESSURE' TEXT," + //血压
                "'BLOOD_SUGAR' TEXT," + //血糖
                "'BLOOD_FAT' TEXT," + //血脂
                "'BLOOD_OXYGEN' TEXT," + // 血氧
                "'ECG_ANALYSIS' TEXT," + // 心电分析
                "'BODY_COMPOSITION' TEXT," + // 人体成分
                "'PHYSIQUE_IDENTIFY' TEXT," + // 体质辨识
                "'PSYCHOLOGICA_ASSESSMENT_SDS' TEXT," + // 心理评估-SDS-抑郁自测
                "'PSYCHOLOGICA_ASSESSMENT_SAS' TEXT," + // 心理评估-SAS-焦虑自测
                "'PSYCHOLOGICA_ASSESSMENT_PSQI' TEXT," + //心理评估-PSQI-睡眠指数
                "'PSYCHOLOGICA_ASSESSMENT_SAQ' TEXT," + // 心理评估-SAQ-自杀指数
                "'PSYCHOLOGICA_ASSESSMENT_UCLA' TEXT," + // 心理评估-UCLA-孤独量表
                "'PSYCHOLOGICA_ASSESSMENT_GCQ' TEXT," + //心理评估-GCQ-舒适状况
                "'PSYCHOLOGICA_ASSESSMENT_SCL90' TEXT," + // 心理评估-SCL90-90项症状
                "'PSYCHOLOGICA_ASSESSMENT_QLSCA' TEXT," + // 心理评估-QLSCA-少儿生活质量
                "'AGEDNESS_SELFCARE' TEXT," + // 老年评估-自理评估
                "'AGEDNESS_DEPRESSION' TEXT," + // 老年评估-抑郁评估
                "'AGEDNESS_INTELLIGENCE' TEXT," + //老年评估-智力评估
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); //更新时间
    


 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_HYPERTENSION' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + // 责任医师
                "'GRXX_SFRQ' TEXT," + //随访日期
                "'GRXX_SFFS' TEXT," + //随访方式
                "'GRXX_GXYLX' TEXT," + // 高血压类型
                "'ZZ' TEXT," + // 症状
                "'TZ_XY' TEXT," + // 体征－血压
                "'TZ_XL' TEXT," + // 体征－心率
                "'TZ_TZ' TEXT," + // 体征－体征
                "'TZ_SG' TEXT," + // 体征－身高
                "'TZ_TZZS' TEXT," + // 体征－体制指数
                "'TZ_QT' TEXT," + // 体征－其他
                "'SHZDFS_RXYL' TEXT," + // 生活指导方式－日吸烟量
                "'SHZDFS_RYJL' TEXT," + // 生活指导方式－日饮酒量
                "'SHZDFS_YDL' TEXT," + // 生活指导方式－运动量
                "'SHZDFS_SYQK' TEXT," + //生活指导方式－摄盐情况
                "'SHZDFS_XLTZ' TEXT," + // 生活指导方式－心理调整
                "'FZJC' TEXT," + // 辅助检查
                "'FYYCX' TEXT," + // 服药依从性
                "'YWBLFY_YWBLFY' INTEGER," + // 药物不良反应
                "'JKWT_YWBLFYMS' TEXT," + // 药物不良反应描述
                "'CCSFFL' TEXT," + // 此次随访分类
                "'YYQK_YY' INTEGER," + // 用药情况
                "'YYQK_YYMS' TEXT," + // 用药情况描述
                "'ZLJY' TEXT," + // 治疗建议
                "'ZZ_SFZZ' INTEGER," + // 转诊－是否转诊
                "'ZZ_SFZZMS' TEXT," + // 转诊－描述
                "'ZZ_JGJKS' TEXT," + // 转诊－机构及科室
                "'ZZHF_RQ' TEXT," + // 转诊回访－日期
                "'ZZHF_BQGSH' TEXT," + // 转诊回访－靶器官损坏
                "'ZZHF_HBZ' TEXT," + // 转诊回访－合并症
                "'ZZHF_QTJB' TEXT," + // 转诊回访－其他疾病
                "'ZZHF_XCSFRQ' TEXT," + // 转诊回访－下次随访日期
                "'ZZHF_SFYSQM' TEXT," + // 转诊回访－随访医生前面
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); //更新时间


 db.execSQL("CREATE TABLE " + constraint + "'ARCHIVE_INFO' (" + //
                "'IDCARD' TEXT PRIMARY KEY NOT NULL ," + //身份证号
                "'NAME' TEXT NOT NULL ," + // 姓名
                "'PICTURE_PATH' TEXT," + // 
                "'ARCHIVE_NO' TEXT," + // 
                "'CARD_NO' TEXT," + // 
                "'GENDER' TEXT," + //  性别
                "'BIRTHDAY' TEXT NOT NULL ," + // 出生日期
                "'RELATIONSHIP' TEXT," + // 与户主关系
                "'WORK_UNIT' TEXT NOT NULL ," + // 工作单位
                "'TELEPHONE' TEXT NOT NULL ," + // 本人电话
                "'ETHNIC' TEXT," + // 名族
                "'CONTACT_NAME' TEXT," + // 联系人
                "'CONTACT_PHONE' TEXT," + // 联系人电话
                "'EDUCATION' TEXT," + // 文化程度
                "'BLOOD_TYPE' TEXT," + //血型
                "'ACCOUNT_PROPERTY' TEXT," + // 户口性质
                "'MARRIAGE' TEXT," + // 婚姻状况
                "'RHNEGATIVE' TEXT," + // RH阴型
                "'JOB' TEXT," + // 职业
                "'NATIONALITY' TEXT," + // 国籍
                "'ASSIST_TYPE' TEXT," + // 援助对象
                "'EMAIL' TEXT," + // 电子邮箱
                "'POSTCODE' TEXT," + // 邮编
                "'FAMILY_ADDRESS' TEXT NOT NULL ," + // 家庭地址
                "'RESIDENT_TYPE' TEXT," + // 常住类型
                "'RESIDENT_ADDRESS' TEXT," + //户籍地址
                "'CERTIFICATE_TYPE' TEXT," + // 证件类型
                "'NEGATIVE_EVENT' TEXT," + // 负性事件
                "'MANAGER_UNIT' TEXT," + // 管理单位
                "'CREATE_DATE' TEXT," + //建档时间
                "'CATEGORY_NAME' TEXT," + // 30: categoryName
                "'CATEGORY_NUM' TEXT," + // 31: categoryNum
                "'INSURANCE_NO' TEXT," + // 医保卡号
                "'SIGN_UP_DATE' TEXT," + // 签约时间
                "'EXPOSURE_HISTORIES' TEXT," + // 暴露史
                "'ALLERGY_HISTORIES' TEXT," + // 过敏史
                "'FAMLIY_HISTORIES' TEXT," + // 家族史
                "'PAST_HISTORIES' TEXT," + // 即往史
                "'SYNTROPHUS_HISTORIES' TEXT," + //遗传病史
                "'BEAR_HISTORIES' TEXT," + // 生育史
                "'MENSES_HISTORIES' TEXT," + // 月经史
                "'DISABILITYIES' TEXT," + // 残疾病史
                "'FAMILY_INFO' TEXT," + // 家庭信息
                "'FAMILY_MERMBER' TEXT," + // 家庭成员
                "'FAMILY_PROBLEM' TEXT," + // 家庭问题
                "'ENVIRONMENT' TEXT);"); // 生活环境

db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_DIABETES_MELLITUS' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + // 个人信息－责任医师
                "'GRXX_SFRQ' TEXT," + // 个人信息－随访日期
                "'GRXX_SFFS' TEXT," + // 个人信息－随访方式
                "'ZZ' TEXT," + // 症状
                "'TZ_XY' TEXT," + //体征－血压
                "'TZ_XL' TEXT," + // 体征－心率
                "'TZ_TZ' TEXT," + // 体征－体重
                "'TZ_SG' TEXT," + // 体征－身高
                "'TZ_TZZS' TEXT," + // 体征－体质实属
                "'TZ_ZBDMBD' TEXT," + // 体征－足背动脉搏动
                "'TZ_YW' TEXT," + // 体征－腰围
                "'TZ_QT' TEXT," + // 体征－其他
                "'SHZDFS_RXYL' TEXT," + // 生活指导方式－日吸烟量
                "'SHZDFS_RYJL' TEXT," + // 生活指导方式－日饮酒量
                "'SHZDFS_YDL' TEXT," + // 生活指导方式－运动量
                "'SHZDFS_ZS' TEXT," + // 生活指导方式－主食
                "'SHZDFS_XLTZ' TEXT," + // 生活指导方式－心理调整
                "'SHZDFS_ZYXW' TEXT," + // 生活指导方式－遵医行为
                "'YYQK' TEXT," + // 用药情况
                "'YDS' TEXT," + // 胰岛素
                "'FZJC' TEXT," + // 辅助检查
                "'FYYCX' TEXT," + // 服药依从性
                "'YWBLFY_SFYWBLFY' INTEGER," + //药物不良反应
                "'JKWT_YWBLFYMS' TEXT," + // 药物不良反应描述
                "'DXTFY' TEXT," + // 低血糖反应
                "'CCSFFL' TEXT," + // 此次随访分类
                "'ZZ_SFZZ' INTEGER," + // 转诊－是否转诊
                "'ZZ_SFZZMS' TEXT," + // 转诊－转诊描述
                "'ZZ_JGJKS' TEXT," + // 转诊－机构及科室
                "'ZZHF_RQ' TEXT," + //  转诊回访－日期
                "'ZZHF_BQGSH' TEXT," + // 转诊回访－？
                "'ZZHF_HBZ' TEXT," +// 转诊回访－合并症
                "'ZZHF_QTJB' TEXT," + // 转诊回访－其他疾病
                "'ZZHF_XCSFRQ' TEXT," + // 转诊回访－下次随访日期
                "'ZZHF_SFYSQM' TEXT," + // 转诊回访－随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新时间

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_STROKE' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + // 个人信息－责任医师
                "'GRXX_SFRQ' TEXT," + // 个人信息－随访日期
                "'SHXGGB_SFXY' INTEGER," + // 生活习惯改变－是否吸烟
                "'SHXGGB_SFXYMS' TEXT," + // 生活习惯改变－是否吸烟描述
                "'SHXGGB_SFYJ' INTEGER," + // 生活习惯改变－是否饮酒
                "'SHXGGB_SFYJMS' TEXT," + // 生活习惯改变－是否饮酒描述
                "'SHXGGB_YDMC' TEXT," + // 生活习惯改变－运动
                "'SHXGGB_YDSJ' TEXT," + //生活习惯改变－运动时间
                "'SHXGGB_KFDLMC' TEXT," + // 生活习惯改变－康复锻炼
                "'SHXGGB_KFDLSJ' TEXT," + // 生活习惯改变－康复锻炼时间
                "'WXYSKZGXY_SFMZCXY' TEXT," + // 危险因素控制高血压－是否每周测血压
                "'WXYSKZGXY_SFMZCXYMS' TEXT," + // 危险因素控制高血压－是否每周测血压描述
                "'WXYSKZGXY_YYQK' TEXT," + // 危险因素控制高血压－用药情况
                "'WXYSKZTNB_FCRQ' TEXT," + // 危险因素控制糖尿病－复查日期
                "'WXYSKZTNB_XTSP' TEXT," + // 危险因素控制高血压－血糖水平
                "'WXYSKZTNB_HBAIC' TEXT," + // 危险因素控制高血压－HBaic
                "'WXYSKZTNB_YYQK' TEXT," + // 危险因素控制高血压－用药情况
                "'WXYSKZGXZ_FCRQ' TEXT," + // 危险因素控制高血脂－复查日期
                "'WXYSKZGXZ_TG' TEXT," + // 危险因素控制高血脂－TG
                "'WXYSKZGXZ_TC' TEXT," + // 危险因素控制高血脂－TC
                "'WXYSKZGXZ_LDL' TEXT," + // 危险因素控制高血脂－LDL
                "'WXYSKZGXZ_HDL' TEXT," + // 危险因素控制高血脂－HDL
                "'WXYSKZGXZ__LP' TEXT," + // 危险因素控制高血脂_LP
                "'WXYSKZGXZ_YYQK' TEXT," + // 危险因素控制高血脂－用药情况
                "'ZDSJ_SFZD' INTEGER," + // 终点事件－是否终点
                "'ZDSJ_XFCZSJZD' TEXT," + // 终点事件－新发卒中事件诊断
                "'ZDSJ_QTXGSJ' TEXT," + //  终点事件－其他血管事件
                "'ZDSJ_YDMC' TEXT," + //  终点事件－
                "'ZDSJ_YDSJ' TEXT," + //  终点事件－
                "'ZDSJ_KFDLMC' TEXT," + //  终点事件－
                "'ZDSJ_KFDLSJ' TEXT," + //  终点事件－
                "'ZDSJ_PF_MRS' TEXT," + //  终点事件－MRs
                "'ZDSJ_PF_MRSJG' TEXT," + // 终点事件－MRS结果
                "'ZDSJ_PF_BI' TEXT," + //  终点事件－Bi
                "'ZDSJ_PF_BIJG' TEXT," + //  终点事件－Bi结果
                "'ZDSJ_PF_SSPF' TEXT," + //  终点事件－评分－膳食评分
                "'ZDSJ_PF_SSPFJG' TEXT," + //  终点事件－评分－膳食评分结果
                "'ZDSJ_SFYSQM' TEXT," + //  终点事件－随访医生签名
                "'CREATE_TIME' TEXT," + //  创建时间
                "'UPDATE_TIME' TEXT);"); // 更新时间


db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_MENTAL_DISEASE' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + // 个人信息-责任医师
                "'GRXX_SFRQ' TEXT," + // 个人信息-随访日期
                "'WXX' TEXT," + // 危险性
                "'ZZ_ZZMC' TEXT," + // 症状
                "'ZZ_ZZL' TEXT," + // 症状-自制力
                "'ZZ_SMQK' TEXT," + // 症状-睡眠情况
                "'ZZ_YSQK' TEXT," + // 症状-饮食情况
                "'SHGNQK_GRSHLL' TEXT," + // 社会功能情况-个人生活料理
                "'SHGNQK_JWHD' TEXT," + // 社会功能情况-家务活动
                "'SHGNQK_XXNL' TEXT," + // 社会功能情况-学习能力
                "'SHGNQK_SHLDJGZ' TEXT," + // 社会功能情况-生成劳动及工作
                "'SHGNQK_SHRJJW' TEXT," + // 社会功能情况-社会人际交往
                "'HZDJTSHDYX_QDZS' TEXT," + //患者对家庭社会的影响-轻度滋事
                "'HZDJTSHDYX_ZHAOS' TEXT," + // 患者对家庭社会的影响-肇事
                "'HZDJTSHDYX_ZS' TEXT," + // 患者对家庭社会的影响-自伤
                "'HZDJTSHDYX_ZSWS' TEXT," + // 患者对家庭社会的影响-自杀未遂
                "'HZDJTSHDYX_GSQK' TEXT," + // 患者对家庭社会的影响-关锁情况
                "'HZDJTSHDYX_MCCYSJ' TEXT," + //患者对家庭社会的影响-末次出院时间
                "'HZDJTSHDYX_ZYQK' TEXT," + // 患者对家庭社会的影响-住院情况
                "'SYSFZJC_JCX' TEXT," + // 实验室（辅助检查）-检查项
                "'SYSFZJC_FYYCX' TEXT," + // 实验室（辅助检查）-服药依从性
                "'SHXGGB_SFYWBLFY' INTEGER," + // 实验室（辅助检查）-是否药物不良反应
                "'SHXGGB_SFYWBLFYMS' TEXT," + // 实验室（辅助检查）-药物不良反应描述
                "'SHXGGB_ZLXG' TEXT," + //实验室（辅助检查）-治疗效果
                "'SHXGGB_SFZZ' INTEGER," + // 实验室（辅助检查）-是否转诊
                "'SHXGGB_SFZZMS' TEXT," + //实验室（辅助检查）-转诊描述
                "'SHXGGB_JGQKS' TEXT," + // 实验室（辅助检查）-机构及科室
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 跟新时间

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_FIRST_PREGNANCY' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_TBRQ' TEXT," + // 个人信息-填表日期
                "'GRXX_YZ' TEXT," + // 个人信息-孕周
                "'GRXX_YC' TEXT," + // 个人信息-孕次
                "'GRXX_YDFMCS' TEXT," + // 个人信息-阴道分娩次数
                "'GRXX_PGCCS' TEXT," + //个人信息-剖宫产次数
                "'GRXX_SFMCYJ' INTEGER," + // 个人信息-是否末次月经
                "'GRXX_SFMCYJMS' TEXT," + // 个人信息-末次月经填写时间？
                "'GRXX_YCQ' TEXT," + // 个人信息-预产期
                "'POXX_ZFXM' TEXT," + //配偶信息-丈夫姓名
                "'POXX_ZFNL' TEXT," + // 配偶信息-丈夫 年龄
                "'POXX_ZFDH' TEXT," + // 配偶信息-丈夫电话
                "'TZ_SG' TEXT," + // 体征-身高
                "'TZ_TZ' TEXT," + // 体征-体重
                "'TZ_TZZS' TEXT," + // 体征-体质指数
                "'TZ_XY' TEXT," + // 体征-血压
                "'JWS' TEXT," + // 既往史
                "'JZSLB' TEXT," + // 家族史类别
                "'JZSZL' TEXT," + // 家族史种类
                "'GRS' TEXT," + // 个人史
                "'FKSSS_SFYGSS' INTEGER," + // 妇科手术史-
                "'FKSSS_SFYGSSMS' TEXT," + // 妇科手术史-描述
                "'YCS_YCS' TEXT," + // 孕产史
                "'TINGZ_SFXZYC' INTEGER," + // 听诊-是否心脏异常
                "'TINGZ_SFXZYCMS' TEXT," + // 听诊-心脏异常描述
                "'TINGZ_SFFBYC' INTEGER," + // 听诊-是否肺部异常
                "'TINGZ_SFFBYCMS' TEXT," + // 听诊-肺部异常描述
                "'FKJC_SFWYYC' INTEGER," + // 妇科检查-是否外阴异常
                "'TINGZ_SFWYYCMS' TEXT," + // 妇科检查-外阴异常描述
                "'FKJC_SFYDYC' INTEGER," + // 妇科检查-是否阴道异常
                "'TINGZ_SFYDYCMS' TEXT," + // 妇科检查-阴道异常描述
                "'FKJC_SFGJYC' INTEGER," + // 妇科检查-是否宫颈异常
                "'TINGZ_SFGJYCMS' TEXT," + // 妇科检查-宫颈异常描述
                "'FKJC_SFZGYC' INTEGER," + // 妇科检查-是否子宫异常
                "'TINGZ_SFZGYCMS' TEXT," + // 妇科检查-子宫异常描述
                "'FKJC_SFFJYC' INTEGER," + // 妇科检查-是否附件异常
                "'TINGZ_SFFJYCMS' TEXT," + // 妇科检查-附件异常描述
                "'FZJC_XHDBZ' TEXT," + // 辅助检查-血红蛋白值
                "'FZJC_BXBJSZ' TEXT," + //  辅助检查-白细胞计数值
                "'FZJC_XXBJSZ' TEXT," + //  辅助检查-血小板计数值
                "'FZJC_XCGQT' TEXT," + //  辅助检查-血常规其他
                "'FZJC_NDB' TEXT," + //  辅助检查-尿蛋白
                "'FZJC_NT' TEXT," + //  辅助检查-尿糖
                "'FZJC_NTT' TEXT," + //  辅助检查-尿酮体
                "'FZJC_NQX' TEXT," + // 辅助检查-尿潜血
                "'FZJC_NCGQT' TEXT," + //  辅助检查-尿蛋白其他
                "'FZJC_XQGBZAM' TEXT," + //  辅助检查-血清谷丙转氨酶
                "'FZJC_BDB' TEXT," + //  辅助检查-白蛋白
                "'FZJC_ZDHS' TEXT," + //  辅助检查-总胆红素
                "'FZJC_JHDHS' TEXT," + //  辅助检查-结合胆红素
                "'FZJC_SFYDFMWYC' INTEGER," + //  辅助检查-是否阴道分泌物异常
                "'FZJC_SFYDFMWYCMS' TEXT," + //  辅助检查-阴道分泌物异常描述
                "'FZJC_MDXQXSY' TEXT," + //  辅助检查-梅毒血清学试验
                "'FZJC__HIVKTJC' TEXT," + //  辅助检查-HIV抗体检测
                "'ZTPG_SFZTPGYC' INTEGER," + // 总体评估是否异常
                "'ZTPG_SFZTPGYCMS' TEXT," + // 总体评估异常描述
                "'ZZ_SFYGZZ' INTEGER," + //转诊-
                "'ZZ_YY' TEXT," + // 转诊-原因
                "'ZZ_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZZ_XCSFRQ' TEXT," + // 转诊-下次随访日期
                "'ZTPG_SFYSQM' TEXT," + // 转诊-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新时间

db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_TWO_TO_FIVE_PREGNANCY' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + // 个人信息-随访日期
                "'GRXX_YZ' TEXT," + // 个人信息-孕周
                "'GRXX_CQFSCS' TEXT," + //个人信息-产前访视次数
                "'GRXX_ZS' TEXT," + // 个人信息-主述
                "'TZ_TZ' TEXT," + // 体征-体重
                "'TZ_XY' TEXT," + // 体征-血压
                "'TZ_XHDB' TEXT," + // 体征-血红蛋白
                "'TZ_NDB' TEXT," + // 体征-尿蛋白
                "'CQJC_GDGD' TEXT," + // 产科检查-宫底高度
                "'CQJC_FW' TEXT," + // 产科检查-腰围
                "'CQJC_TXL' TEXT," + // 产科检查-胎心率
                "'QTJC_SFBC' INTEGER," + // 其他检查-是否B超
                "'QTJC_SFBCMS' TEXT," + // 其他检查-是否B超描述
                "'QTJC_SFXTSC' INTEGER," + // 其他检查-是否血糖筛查
                "'QTJC_SFXTSCMS' TEXT," + // 其他检查-是否血糖筛查描述
                "'ZD_NAME' TEXT," + // 指导
                "'ZZ_SFYGZZ' INTEGER," + // 转诊-
                "'ZZ_YY' TEXT," + // // 转诊-原因
                "'ZZ_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZZ_XCSFRQ' TEXT," + // 转诊-下次随访日期
                "'ZTPG_SFYSQM' TEXT," + // 转诊-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_POSTPARTUM' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + // 随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + //个人信息-访视日期
                "'JBJC_TW' TEXT," + // 基本检查-体温
                "'JBJC_XY' TEXT," + // 基本检查-血压
                "'JBJC_MB' TEXT," + // 基本检查-脉搏
                "'XLJKZK_XLZK' TEXT," + //心理健康状况-心理状况
                "'XLJKZK_SMZK' TEXT," + // 心理健康状况-睡眠状况
                "'XLJKZK_JKZK' TEXT," + // 心理健康状况-健康状况
                "'RFJC_ZCRX' TEXT," + // 乳房检查-左侧乳腺
                "'RFJC_YCRX' TEXT," + // 乳房检查-右侧乳腺
                "'CHJC_SFELYC' INTEGER," + // 产后检查-是否恶露异常
                "'CHJC_SFELYCMS' TEXT," + //  产后检查-恶露异常描述
                "'CHJC_SFZGYC' INTEGER," + //  产后检查-是否子宫异常
                "'CHJC_SFZGYCMS' TEXT," + //  产后检查-子宫异常描述
                "'CHJC_SFSKYC' INTEGER," + // 产后检查-是否伤口异常
                "'CHJC_SFSKYCMS' TEXT," + //  产后检查-伤口异常描述
                "'CHJC_SKYHZK' TEXT," + //  产后检查-伤口愈合状况
                "'CHJC_QT' TEXT," + //  产后检查-其他
                "'ZD_NAME' TEXT," + //指导
                "'ZZ_SFYGZZ' INTEGER," + // 转诊-
                "'ZZ_YY' TEXT," + // // 转诊-原因
                "'ZZ_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZZ_XCSFRQ' TEXT," + // 转诊-下次随访日期
                "'ZTPG_SFYSQM' TEXT," + // 转诊-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_FORTY_TWO' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + //  个人信息-责任医师
                "'GRXX_SFRQ' TEXT," + //  个人信息-随访日期
                "'GRXX_SFFS' TEXT," + // 个人信息-随访方式
                "'GRXX_GXYLX' TEXT," + // 5: grxx_gxylx
                "'JBJC_TW' TEXT," + //基本检查-体温
                "'JBJC_XY' TEXT," + // 基本检查-血压
                "'JBJC_MB' TEXT," + //基本检查-脉搏
                "'JBJC_TZ' TEXT," + // 基本检查-体重
                "'XLJKZK_XLZK' TEXT," + //心理健康状况-心理状况
                "'XLJKZK_SMZK' TEXT," + // 心理健康状况-睡眠状况
                "'XLJKZK_JKZK' TEXT," + //心理健康状况-健康状况
                "'RFJC_ZCRX' TEXT," + // 乳房检查-左侧
                "'RFJC_YCRX' TEXT," + //乳房检查-右侧
                "'CHJC_SFELYC' INTEGER," + // 产后检查-是否恶露异常
                "'CHJC_SFELYCMS' TEXT," + //  产后检查-恶露异常描述
                "'CHJC_SFZGYC' INTEGER," + //  产后检查-是否子宫异常
                "'CHJC_SFZGYCMS' TEXT," + //  产后检查-子宫异常描述
                "'CHJC_SFSKYC' INTEGER," + // 产后检查-是否伤口异常
                "'CHJC_SFSKYCMS' TEXT," + //  产后检查-伤口异常描述
                "'CHJC_SKYHZK' TEXT," + //  产后检查-伤口愈合状况
                "'CHJC_QT' TEXT," + //  产后检查-其他
                "'ZD_NAME' TEXT," + //指导
                "'ZZ_SFYGZZ' INTEGER," + // 转诊-
                "'ZZ_YY' TEXT," + // // 转诊-原因
                "'ZZ_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZZ_XCSFRQ' TEXT," + // 转诊-下次随访日期
                "'ZTPG_SFYSQM' TEXT," + // 转诊-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期



db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_NEWBORN' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + // 个人信息-访视日期
                "'GRXX_FSCS' TEXT," + // 个人信息-访视次数
                "'GRXX_CSRL' TEXT," + // 个人信息-出生日龄
                "'GRXX_ZRYS' TEXT," + // 个人信息-责任医师
                "'WYQK_WYFS' TEXT," + //喂养情况-喂养方式
                "'WYQK_CNL' TEXT," + //喂养情况-吃奶量
                "'WYQK_CNCS' TEXT," + //喂养情况-吃奶次数
                "'WYQK_SFOT' INTEGER," + //喂养情况-是否呕吐
                "'JBSTZK_DBXZ' TEXT," + // 基本身体状况-大便性状
                "'JBSTZK_DBCS' TEXT," + // 基本身体状况-大便次数
                "'JBSTZK_DBYS' TEXT," + // 基本身体状况-大便颜色
                "'JBSTZK_SMWT' TEXT," + // 基本身体状况-睡眠问题
                "'GWE_SFGWE' INTEGER," + // 是否高危儿
                "'TGJCQK_MQTZ' TEXT," + // 体格检查情况-目前体重
                "'TGJCQK_TW' TEXT," + // 体格检查情况-体温
                "'TGJCQK_SC' TEXT," + // 体格检查情况-身长
                "'TGJCQK_HXPL' TEXT," + // 体格检查情况-呼吸频率
                "'TGJCQK_ML' TEXT," + // 体格检查情况-脉率
                "'TGJCQK_MS' TEXT," + // 体格检查情况-面色
                "'TGJCQK_HDBW' TEXT," + // 体格检查情况-黄疸部位
                "'TGJCQK_QX' TEXT," + // 体格检查情况-前？
                "'TGJCQK_QXJL' TEXT," + // 体格检查情况-前？结论
                "'TGJCQK_SFYWGYC' INTEGER," + // 体格检查情况-是否眼外观异常
                "'TGJCQK_SFYWGYCMS' TEXT," + // 体格检查情况-眼外观异常描述
                "'TGJCQK_SFEWGYC' INTEGER," + // 体格检查情况-是否耳外观异常
                "'TGJCQK_SFEWGYCMS' TEXT," + // 体格检查情况-耳外观异常描述
                "'TGJCQK_SFBYC' INTEGER," + // 体格检查情况-是否鼻异常
                "'TGJCQK_SFBYCMS' TEXT," + // 体格检查情况-鼻异常描述
                "'TGJCQK_SFKQYC' INTEGER," + // 体格检查情况-是否口腔异常
                "'TGJCQK_SFKQYCMS' TEXT," + // 体格检查情况-口腔异常描述
                "'TGJCQK_SFXFTZYC' INTEGER," + // 体格检查情况-是否心肺听诊异常
                "'TGJCQK_SFXFTZYCMS' TEXT," + // 体格检查情况-心肺听诊异常描述
                "'TGJCQK_SFFBCZYC' INTEGER," + // 体格检查情况-是否肺部触诊异常
                "'TGJCQK_SFFBCZYCMS' TEXT," + //体格检查情况-肺部触诊异常描述
                "'TGJCQK_SFSZHDDYC' INTEGER," + //体格检查情况-是否四肢活动度异常
                "'TGJCQK_SFSZHDDYCMS' TEXT," + // 体格检查情况-四肢活动度异常描述
                "'TGJCQK_SFJBBKYC' INTEGER," + // 体格检查情况-是否颈部包块异常
                "'TGJCQK_SFJBBKYCMS' TEXT," + // 体格检查情况-颈部包块异常描述
                "'TGJCQK_PF' TEXT," + // 体格检查情况-皮肤
                "'TGJCQK_SFGMYC' INTEGER," + // 体格检查情况-是否肝门异常
                "'TGJCQK_SFGMYCMS' TEXT," + // 体格检查情况-肛门异常描述
                "'TGJCQK_SFWSZQYC' INTEGER," + // 体格检查情况-是否外生殖器异常
                "'TGJCQK_SFWSZQYCMS' TEXT," + //体格检查情况-外生殖器异常描述
                "'TGJCQK_SFJZYC' INTEGER," + // 体格检查情况-是否脊柱异常
                "'TGJCQK_SFJZYCMS' TEXT," + // 体格检查情况-脊柱异常描述
                "'TGJCQK_SFQDYC' INTEGER," + // 体格检查情况-是否脐带异常
                "'TGJCQK_SFQDYCMS' TEXT," + //体格检查情况-脐带异常描述
                "'ZZJY_SFZZ' INTEGER," + // 转诊-是否转诊
                "'ZZJY_YY' TEXT," + // 转诊-转诊原因
                "'ZZJY_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZD_ZDMC' TEXT," + // 指导-
                "'ZD_TSYZJCL' TEXT," + // 指导-特殊医嘱及处理
                "'ZD_XCSFRQ' TEXT," + // 指导-下次随访日期
                "'ZD_XCFSDD' TEXT," + // 指导-下次随访地点
                "'ZD_SFYSQM' TEXT," + //指导-随访医师签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_ONE_NEWBORN' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + // 个人信息-访视日期
                "'GRXX_YL' TEXT," + // 个人信息-月亮
                "'GRXX_ZRYS' TEXT," + // 个人信息-责任医师
                "'WYQK_WYFS' TEXT," + //喂养情况-喂养方式
                "'WYQK_WMRCS' TEXT," + // 喂养情况-喂母乳次数
                "'WYQK_NF' TEXT," + //喂养情况-奶粉
                "'WYQK_MFHZ' TEXT," + //喂养情况-米粉或粥
                "'WYQK_CNGN' TEXT," + // 喂养情况-菜泥果泥
                "'WYQK_DHHGN' TEXT," + // 喂养情况-蛋黄或肝泥
                "'WYQK_TJFS' TEXT," + //喂养情况-添加辅食
                "'WYQK_CS' TEXT," + // 喂养情况-次数
                "'WYQK_SY' TEXT," + //喂养情况-食欲
                "'WYQK_FYWSSD' TEXT," + // 喂养情况-服用维生素D
                "'TGJCQK_TZ' TEXT," + // 体格检查情况-体重
                "'TGJCQK_SC' TEXT," + // 体格检查情况-身高
                "'TGJCQK_BMI' TEXT," + //体格检查情况-BMI
                "'TGJCQK_YW' TEXT," + // 体格检查情况-腰围
                "'TGJCQK_YYPJ' TEXT," + // 体格检查情况-营养评价
                "'TGJCQK_MS' TEXT," + // 体格检查情况-面色
                "'TGJCQK_SFQXBH' INTEGER," + // 体格检查情况-是否前？闭合
                "'TGJCQK_SFQXBHMS' TEXT," + //体格检查情况-前？闭合描述
                "'TGJCQK_SFJBBKYC' INTEGER," + // 体格检查情况-是否颈部包块异常
                "'TGJCQK_SFJBBKYCMS' TEXT," + // 体格检查情况-颈部包块异常描述
                "'TGJCQK_SFPFYC' INTEGER," + // 体格检查情况-是否皮肤异常
                "'TGJCQK_SFPFYCMS' TEXT," + // 体格检查情况-皮肤异常描述
                "'TGJCQK_SFYWGYC' INTEGER," + // 体格检查情况-是否眼外观异常
                "'TGJCQK_SFYWGYCMS' TEXT," + // 体格检查情况-眼外观异常描述
                "'TGJCQK_SFEWGYC' INTEGER," + // 体格检查情况-是否耳外观异常
                "'TGJCQK_SFEWGYCMS' TEXT," + // 体格检查情况-耳外观异常描述
                "'TGJCQK_SFBYC' INTEGER," + // 体格检查情况-是否鼻异常
                "'TGJCQK_SFBYCMS' TEXT," + // 体格检查情况-鼻异常描述
                "'TGJCQK_SFKQYC' INTEGER," + // 体格检查情况-是否口腔异常
                "'TGJCQK_SFKQYCMS' TEXT," + // 体格检查情况-口腔异常描述
                "'TGJCQK_SFTLYC' INTEGER," + // 体格检查情况-是否听力异常
                "'TGJCQK_SFTLYCMS' TEXT," + // 体格检查情况-听力异常描述
                "'TGJCQK_SFXFTZYC' INTEGER," + // 体格检查情况-是否心肺听诊异常
                "'TGJCQK_SFXFTZYCMS' TEXT," + // 体格检查情况-心肺听诊异常描述
                "'TGJCQK_SFFBCZYC' INTEGER," + // 体格检查情况-是否肺部触诊异常
                "'TGJCQK_SFFBCZYCMS' TEXT," + //体格检查情况-肺部触诊异常描述
                "'TGJCQK_SFSZHDDYC' INTEGER," + //体格检查情况-是否四肢活动度异常
                "'TGJCQK_SFSZHDDYCMS' TEXT," + // 体格检查情况-四肢活动度异常描述
                "'TGJCQK_QB' TEXT," + // 体格检查情况-脐部
                "'TGJCQK_GLBZZ' TEXT," + // 体格检查情况-佝偻病症状
                "'TGJCQK_SFWSZQYC' INTEGER," + // 体格检查情况-是否外生殖器异常
                "'TGJCQK_SFWSZQYCMS' TEXT," + //体格检查情况-外生殖器异常描述
                "'TGJCQK_SFGMYC' INTEGER," + // 体格检查情况-是否肝门异常
                "'TGJCQK_SFGMYCMS' TEXT," + // 体格检查情况-肛门异常描述
                "'TGJCQK_KGJHD' TEXT," + // 体格检查情况-髋关节活动
                "'FYSC_SFFWTT' INTEGER," + // 发育筛选-是否俯卧抬头
                "'FYSC_SFDYSHWX' INTEGER," + //发育筛选-是否逗引时会微笑
                "'FYSC_SFJBSNZJKZT' INTEGER," + // 发育筛选-是否竖抱时能自己控制头
                "'FYSC_SFSZK' INTEGER," + //发育筛选-手张开
                "'FYSC_SFXCS' INTEGER," + //发育筛选-笑出声
                "'FYSC_SFSSZWJ' INTEGER," + // 发育筛选-伸手抓玩具
                "'FYSC_SFHFS' INTEGER," + //发育筛选-会翻身
                "'FYSC_SFZJZSDZJWX' INTEGER," + // 发育筛选-照镜子时会自己微笑
                "'FYSC_SFRS' INTEGER," + //发育筛选-人生
                "'FYSC_SFYSZCZ' INTEGER," + //发育筛选-用手支撑坐
                "'XHDBZ' TEXT," + // 血红蛋白值
                "'SYSJC' TEXT," + // 实验室检查
                "'FZJC' TEXT," + // 辅助检查
                "'HWHD' TEXT," + // 户外活动
                "'FYPG_SFTG' INTEGER," + // 发育评估
                "'HBQK_SFHB' INTEGER," + //患病情况-
                "'HBQK_SFHBMS' TEXT," + // 患病情况-描述
                "'JBZD' TEXT," + // 疾病诊断
                "'YYXJBGL_SFYYXJB' INTEGER," + //营养性疾病管理
                "'YYXJBGL_SFGWE' INTEGER," + // 高位儿童管理
                "'ZZJY_SFZZ' INTEGER," + // 转诊-是否转诊
                "'ZZJY_YY' TEXT," + // 转诊-转诊原因
                "'ZZJY_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZD_ZD' TEXT," + // 指导
                "'ZD_YZHJY' TEXT," + // 医嘱和建议
                "'ZD_XCFSDD' TEXT," + // 指导-下次随访地点
                "'ZD_SFYSQM' TEXT," + //指导-随访医师签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_ONE_TWO_NEWBORN' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + // 个人信息-访视日期
                "'GRXX_YNL' TEXT," + // 个人信息-月年龄
                "'GRXX_CSRL' TEXT," + // 个人信息-出生日龄
                "'GRXX_ZRYS' TEXT," + // 个人信息-责任医师
                "'WYQK_SFMRYY' INTEGER," + // 喂养情况-是否母乳喂养
                "'WYQK_SFMRYYMS' TEXT," + // 喂养情况-母乳喂养描述
                "'WYQK_ZCCS' TEXT," + // 喂养情况-主餐次数
                "'WYQK_NNNF' TEXT," + // 喂养情况-牛奶奶粉
                "'WYQK_D' TEXT," + // 喂养情况-蛋
                "'WYQK_RLHYX' TEXT," + // 喂养情况-鱼类或虾类
                "'WYQK_SGHSC' TEXT," + // 喂养情况-水果和蔬菜
                "'WYQK_SY' TEXT," + // 喂养情况-食欲
                "'WYQK_FYWSSD' TEXT," + // 喂养情况-服用维生素D
                "'TGJCQK_TZ' TEXT," + // 体格检查情况-体重
                "'TGJCQK_SC' TEXT," + // 体格检查情况-身长
                "'TGJCQK_BMI' TEXT," + // 体格检查情况-BMI
                "'TGJCQK_TW' TEXT," + // 体格检查情况-头围
                "'TGJCQK_YYPJ' TEXT," + // 体格检查情况-营养评价
                "'TGJCQK_MS' TEXT," + // 体格检查情况-面色
                "'TGJCQK_SFQXBH' INTEGER," + // 体格检查情况-是否前？闭合
                "'TGJCQK_SFQXBHMS' TEXT," + //体格检查情况-前？闭合描述
                "'TGJCQK_SFPFYC' INTEGER," + // 体格检查情况-是否皮肤异常
                "'TGJCQK_SFPFYCMS' TEXT," + // 体格检查情况-皮肤异常描述
                "'TGJCQK_SFYWGYC' INTEGER," + // 体格检查情况-是否眼外观异常
                "'TGJCQK_SFYWGYCMS' TEXT," + // 体格检查情况-眼外观异常描述
                "'TGJCQK_SFEWGYC' INTEGER," + // 体格检查情况-是否耳外观异常
                "'TGJCQK_SFEWGYCMS' TEXT," + // 体格检查情况-耳外观异常描述
                "'TGJCQK_SFTLYC' INTEGER," + // 体格检查情况-是否听力异常
                "'TGJCQK_SFTLYCMS' TEXT," + // 体格检查情况-听力异常描述
                "'TGJCQK_CYQCS' TEXT," + // 体格检查情况-出牙
                "'TGJCQK_CYQCSJL' TEXT," + // 体格检查情况-出牙结论
                "'TGJCQK_SFXFTZYC' INTEGER," + // 体格检查情况-是否心肺听诊异常
                "'TGJCQK_SFXFTZYCMS' TEXT," + // 体格检查情况-心肺听诊异常描述
                "'TGJCQK_SFFBCZYC' INTEGER," + // 体格检查情况-是否肺部触诊异常
                "'TGJCQK_SFFBCZYCMS' TEXT," + //体格检查情况-肺部触诊异常描述
                "'TGJCQK_SFSZHDDYC' INTEGER," + //体格检查情况-是否四肢活动度异常
                "'TGJCQK_SFSZHDDYCMS' TEXT," + // 体格检查情况-四肢活动度异常描述
                "'TGJCQK_SFWSZQYC' INTEGER," + // 体格检查情况-是否外生殖器异常
                "'TGJCQK_SFWSZQYCMS' TEXT," + //体格检查情况-外生殖器异常描述
                "'TGJCQK_GLBZZ' TEXT," + // 体格检查情况-佝偻病症状
                "'FYSC_HYYSJBBMM' INTEGER," + //发育筛查-会有意识叫爸爸妈妈
                "'FYSC_HYSZRDXHTP' INTEGER," + // 发育筛查-会用手指人东西或图片
                "'FYSC_ZJDZW' INTEGER," + //发育筛查-自己读走稳
                "'FYSC_HJWJFDBZHWL' INTEGER," + // 发育筛查-会将玩具放到杯子会碗中
                "'FYSC_NZJDXBZQL' INTEGER," + // 发育筛查-能自己蹲下并站起来
                "'FYSC_HSSGYSDDZ' INTEGER," + // 发育筛查-会说四个以上单词
                "'FYSC_HZRZJWGHSTBW' INTEGER," + // 发育筛查-会指认五官
                "'FYSC_NWCJDZLRBQFZS' INTEGER," + // 发育筛查-能完成简单指令
                "'FYSC_HZJP' INTEGER," + // 发育筛查-会自己跑
                "'FYSC_HDG37KJMHWJ' INTEGER," + // 发育筛查-会玩积木
                "'XHDBZ' TEXT," + // 血红蛋白值
                "'SYSJC' TEXT," + // 实验室检查
                "'FZJC' TEXT," + // 辅助检查
                "'HWHD' TEXT," + // 户外活动
                "'FYPG_SFTG' INTEGER," + // 发育评估
                "'HBQK_SFHB' INTEGER," + //患病情况-
                "'HBQK_SFHBMS' TEXT," + // 患病情况-描述
                "'JBZD' TEXT," + // 疾病诊断
                "'YYXJBGL_SFYYXJB' INTEGER," + //营养性疾病管理
                "'YYXJBGL_SFGWE' INTEGER," + // 高位儿童管理
                "'ZZJY_SFZZ' INTEGER," + // 转诊-是否转诊
                "'ZZJY_YY' TEXT," + // 转诊-转诊原因
                "'ZZJY_JGJKS' TEXT," + // 转诊-机构及科室
                "'ZD_ZD' TEXT," + // 指导
                "'ZD_YZHJY' TEXT," + // 医嘱和建议
                "'ZD_XCFSDD' TEXT," + // 指导-下次随访地点
                "'ZD_SFYSQM' TEXT," + //指导-随访医师签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_THREE_SIX_NEWBORN' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_FSRQ' TEXT," + // 个人信息-访视日期
                "'GRXX_NL' TEXT," + // 个人信息-年龄
                "'GRXX_SJNL' TEXT," + // 个人信息-实际年龄
                "'GRXX_ZRYS' TEXT," + // 个人信息-责任医师
                "'TGJCQK_TZ' TEXT," + // 体格检查情况-体重
                "'TGJCQK_SC' TEXT," + // 体格检查情况-身长
                "'TGJCQK_BMI' TEXT," + // 体格检查情况-BMI
                "'TGJCQK_TGFYPJ' TEXT," + // 体格检查情况-体格发育评价
                "'TGJCQK_SL' TEXT," + //体格检查情况-视力
                "'TGJCQK_CYQCS' TEXT," + // 体格检查情况-口腔出牙
                "'TGJCQK_CYQCSJL' TEXT," + // 体格检查情况-口腔出牙结论
                "'TGJCQK_SFTLYC' INTEGER," + // 体格检查情况-听力异常
                "'TGJCQK_SFTLYCMS' TEXT," + // 体格检查情况-异常描述
                "'TGJCQK_SFYWGYC' INTEGER," + // 体格检查情况-眼部
                "'TGJCQK_SFYWGYCMS' TEXT," + // 体格检查情况-眼部描述
                "'TGJCQK_SFEBYC' INTEGER," + // 体格检查情况-耳鼻异常
                "'TGJCQK_SFEBYCMS' TEXT," + // 体格检查情况-耳鼻异描述
                "'TGJCQK_SFXFTZYC' INTEGER," + // 体格检查情况-是否心肺听诊异常
                "'TGJCQK_SFXFTZYCMS' TEXT," + // 体格检查情况-心肺听诊异常描述
                "'TGJCQK_SFFBCZYC' INTEGER," + // 体格检查情况-是否腹部触诊异常
                "'TGJCQK_SFFBCZYCMS' TEXT," + //体格检查情况-腹部触诊异常描述
                "'TGJCQK_SFWSZQYC' INTEGER," + // 体格检查情况-是否外生殖器异常
                "'TGJCQK_SFWSZQYCMS' TEXT," + //体格检查情况-外生殖器异常描述
                "'TGJCQK_SFGGYCTZYC' INTEGER," + // 体格检查情况-是否骨骼异常
                "'TGJCQK_SFGGYCTZYCMS' TEXT," + // 体格检查情况-是否骨骼异常描述
                "'XHDBZ' TEXT," + // 血红蛋白值
                "'SYSJC' TEXT," + // 实验室检查
                "'FZJC' TEXT," + // 辅助检查
                "'HBQK' TEXT," + // 患病情况
                "'YYXJBGL_SFYYXJB' INTEGER," + //营养性疾病管理
                "'JBZD' TEXT," + // 疾病诊断
                "'ZZ_SFZZ' INTEGER," +// 转诊-是否转诊
                "'ZZ_YY' TEXT," +// 转诊-转诊原因
                "'ZZ_JGJKS' TEXT," +  // 转诊-机构及科室
                "'ZD_ZD' TEXT," + // 指导
                "'ZD_YZHJY' TEXT," + // 医嘱和建议
                "'ZD_XCSFRQ' TEXT," + // 下次随访日期
                "'ZD_SFYSQM' TEXT," + //指导-随访医师签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

 db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_AGED' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + //  个人信息-责任医师
                "'GRXX_SFRQ' TEXT," + //  个人信息-随访日期
                "'GRXX_SFZQJY' TEXT," + // 个人信息-随访周期建议
                "'GRXX_SFFS' TEXT," + // 个人信息-随访方式
                "'GRXX_SFXZ' TEXT," + // 个人信息-随访性质
                "'GRXX_XLZT' TEXT," + // 个人信息-心理状态
                "'ZZ' TEXT," + // 症状
                "'TZ_XY' TEXT," + // 体征-血压
                "'TZ_XL' TEXT," + //体征-心率
                "'TZ_YW' TEXT," + // 体征-腰围
                "'TZ_SG' TEXT," + // 体征-身高
                "'TZ_TZ' TEXT," + // 体征-体重
                "'TZ_TZZS' TEXT," + // 体征-体质指数
                "'TZ_XZDGC' TEXT," + //体征-血总胆固醇
                "'TZ_GMDDB' TEXT," + // 体征-高密度血蛋白
                "'TZ_XGYSZ' TEXT," + // 体征-血甘油三酯
                "'TZ_DMDDB' TEXT," + //体征-低密度脂蛋白
                "'TZ_KFXT' TEXT," + // 体征-空腹血糖
                "'TZ_QT' TEXT," + // 体征-其他
                "'SHFSZD_SFXY' INTEGER," + //生活方式-是否吸烟
                "'SHFSZD_SFXYMS' TEXT," + // 生活方式-吸烟描述
                "'SHFSZD_SFYJ' INTEGER," + // 生活方式-是否饮酒
                "'SHFSZD_SFYJMS' TEXT," + // 生活方式-饮酒描述
                "'SHFSZD_SFYD' INTEGER," + //生活方式-是否运动
                "'SHFSZD_SFYDMS' TEXT," + // 生活方式-运动描述
                "'SHFSZD_YDXM' TEXT," + // 生活方式-运动项目
                "'SHFSZD_SYQK' TEXT," + // 生活方式-摄盐情况
                "'SHFSZD_XLTZ' TEXT," + // 生活方式-心理调整
                "'SHFSZD_ZYXW' TEXT," + // 生活方式-遵医行为
                "'SHFSZD_MQQK' TEXT," + // 生活方式-目前情况    
                "'SHFSZD_SFFWNR' TEXT," + //生活方式-随访服务内容
                "'SHFSZD_SFPG' TEXT," + //生活方式-随访评估
                "'SHFSZD_XCSFRQ' TEXT," + //生活方式-下次随访日期
                "'SHFSZD_SFYSQM' TEXT," + // 生活方式-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期

db.execSQL("CREATE TABLE " + constraint + "'FOLLOW_UP_DISABLED_PERSON' (" + //
                "'FOLLOW_UP_NO' TEXT PRIMARY KEY NOT NULL ," + //随访编号
                "'IDCARD' TEXT NOT NULL ," + // 身份证
                "'GRXX_ZRYS' TEXT," + //  个人信息-责任医师
                "'GRXX_SFRQ' TEXT," + //  个人信息-随访日期
                "'GRXX_SFFS' TEXT," + // 个人信息-随访方式
                "'GRXX_KNQT' TEXT," + // 个人信息-困难群体
                "'GRXX_KFXQ' TEXT," + // 个人信息-康复需求
                "'CJNX_CJZBH' TEXT," + // 残疾类型-残疾证编号
                "'CJNX_ZYCJ' TEXT," + // 残疾类型-主要残疾
                "'CJNX_DCCJ' TEXT," + //残疾类型-多重残疾
                "'CJNX_CJRQ' TEXT," + // 残疾类型-残疾日期
                "'CJNX_CJCD' TEXT," + // 残疾类型-残疾程度
                "'CJNX_CJYY' TEXT," + // 残疾类型-残疾原因
                "'YBQK_ZZ' TEXT," + //一般情况-症状
                "'YBQK_TZ' TEXT," + // 一般情况-体重
                "'YBQK_XY' TEXT," + // 一般情况-血压
                "'YBQK_XL' TEXT," + // 一般情况-心率
                "'YBQK_QT' TEXT," + // 一般情况-其他
                "'KFFW_KFXM' TEXT," + // 康复服务-康复项目
                "'KFFW_KFMB' TEXT," + // 康复服务-康复目标
                "'KFFW_GNXL' TEXT," + // 康复服务-功能训练
                "'KFFW_XLDZ' TEXT," + // 康复服务-训练地址
                "'KFFW_XLPG' TEXT," + // 康复服务-训练评估
                "'KFFW_YY' TEXT," + // 康复服务-原因
                "'KFFW_ZJQX' TEXT," + // 康复服务-转介去向
                "'SHFSZD_SFXY' INTEGER," + //生活方式-是否吸烟
                "'SHFSZD_SFXYMS' TEXT," + // 生活方式-吸烟描述
                "'SHFSZD_SFYJ' INTEGER," + // 生活方式-是否饮酒
                "'SHFSZD_SFYJMS' TEXT," + // 生活方式-饮酒描述
                "'SHFSZD_SFYD' INTEGER," + //生活方式-是否运动
                "'SHFSZD_SFYDMS' TEXT," + // 生活方式-运动描述
                "'SHFSZD_YDXM' TEXT," + // 生活方式-运动项目
                "'SHFSZD_SYQK' TEXT," + // 生活方式-摄盐情况
                "'SHFSZD_XLTZ' TEXT," + // 生活方式-心理调整
                "'SHFSZD_ZYXW' TEXT," + // 生活方式-遵医行为
                "'SHFSZD_SFPG' TEXT," + //生活方式-随访评估
                "'SHFSZD_KFJY' TEXT," + //生活方式-康复建议
                "'SHFSZD_XCSFRQ' TEXT," + //生活方式-下次随访日期
                "'SHFSZD_SFYSQM' TEXT," + // 生活方式-随访医生签名
                "'CREATE_TIME' TEXT," + // 创建时间
                "'UPDATE_TIME' TEXT);"); // 更新日期
               

    } 
    
}
