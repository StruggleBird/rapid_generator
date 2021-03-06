package yunnex.util;

import java.io.FileInputStream;
import java.util.Properties;

import cn.org.rapid_framework.generator.GeneratorProperties;
import cn.org.rapid_framework.generator.provider.db.table.model.Table;

/**
 * 自动生成代码的规范配置工具类
 * 
 * @author Shuah
 * @date 2014年3月21日
 */
public class RapidUtil {
    /**
     * 规则配置文件路径
     */
    private static final String CONFIG_PATH = "src/main/resources/profile.properties";
    /**
     * 加载规则配置属性信息对象
     */
    private static final Properties props;
    /**
     * 是否启用RapidUtil相关配置设置
     */
    private static final boolean isEnableConf;
    /**
     * 是否包含mongoDB表 (当设为Y时，在mysql中主键为varchar的则会自动生成mongoDB相关代码)
     */
    private static final boolean isEnableMongo;
    /**
     * 是否改为SOA风格的代码生成
     */
    private static final boolean isSoaStyle;
    /**
     * 得到模块名 如module
     */
    private static final String moduleName;
    
    
    /**
     * 初始化加载相关规则配置
     */
    static {
        props = new Properties();
        try {
            props.load(new FileInputStream(CONFIG_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
        isEnableConf = ("Y".equalsIgnoreCase(props.getProperty("is_enable_conf")));
        isEnableMongo = ("Y".equalsIgnoreCase(props.getProperty("is_enable_mongo")));
        isSoaStyle = ("Y".equalsIgnoreCase(props.getProperty("is_soa_style")));
        moduleName = props.getProperty("modulepackage", "module");
    }
    
    /**
     * 判断是否为mongoDB中的表
     * @param table 表信息
     * @return boolean
     */
    public static boolean isMongoTable(Table table) {
        if (!isEnableMongo) {
            return false;
        }
        return ("String".equals(table.getPkColumn().getAsType()));
    }
    
    /**
     * 设置表的分组名(用于生成对应包名)
     * @param table 表信息
     */
    public static void setTableGroupName(Table table) {
        if (!isEnableConf) {
            table.setOwnerSynonymName(""); //这里暂用该栏位来存放组名
            return;
        }
        String tableName = table.getSqlName(); //得到表名
        String groupName = props.getProperty(tableName, tableName.split("_")[0]); //获取类的组名 默认为首单词
        //如果CONFIG_PATH文件中配置了group_name,则使用这个配置，否则使用表名的首单词
        if(props.get("group_name") != null ){
        		if(props.get("group_name").toString().equals("null")) //如果值为null，则意为不使用groupName
        		{
        				groupName = null;
        		}
        		else if( props.get("group_name").toString().trim().length() != 0)//否则如果有值，则使用该值为groupName
        		{
        			groupName = props.get("group_name").toString();
        		}
        }
        
        if (!isSoaStyle) {
            table.setOwnerSynonymName("." + groupName); //这里用来存放组名
            GeneratorProperties.setProperty("grouppackage", groupName);
        } else {
            table.setOwnerSynonymName("");
            GeneratorProperties.setProperty("grouppackage", "");
//            GeneratorProperties.setProperty("modulepackage", "." + moduleName + "." + groupName);
            GeneratorProperties.setProperty("modulepackage", "." + moduleName + (groupName == null ? "" : ("." + groupName)));
        }
    }
    
    /**
     * 用于过滤不需要生成的目录(如mongoDB表只生成对应的代码不需要生成mybatis相关代码等)
     * @param path 文件路径
     * @param mongoFlg 是否为mongoDB表
     * @return boolean
     */
    public static boolean isErrorValidPath(String path, boolean mongoFlg) {
        return (path.contains("${") && (mongoFlg && !path.contains("mongo_") || !mongoFlg && path.contains("mongo_")));
    }
    
}
