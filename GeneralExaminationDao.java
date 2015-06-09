
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
    }
    } 
    
}
