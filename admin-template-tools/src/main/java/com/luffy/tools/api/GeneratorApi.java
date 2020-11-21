package com.luffy.tools.api;

import bean.ResultBean;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.luffy.tools.mapper.TableMapper;
import com.luffy.tools.model.Table;
import enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system")
public class GeneratorApi {

    @Autowired
    private TableMapper tableMapper;


    @GetMapping("table")
    public ResultBean<List<Table>> queryTableName() {
        ResultBean<List<Table>> resultBean = new ResultBean<>(ResultCode.SUCCESS);
        resultBean.setData(tableMapper.queryAllTableName());
        return resultBean;
    }

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true).setFileOverride(false).setAuthor("luffy").setOpen(false)
                .setOutputDir(System.getProperty("user.dir") + "/admin-template-tools/src/main/java")
                .setServiceName("%sService").setFileOverride(true);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/admin-template");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("luffy1994");

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.luffy")
                .setEntity("model")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setXml("mapper");

        // 自定义文件存储路径
        Map<String, String> configPathInfo = new HashMap<>(6);
        configPathInfo.put("entity_path", System.getProperty("user.dir") + "/admin-template-common/src/main/java/com/luffy/model");
        configPathInfo.put("mapper_path", System.getProperty("user.dir") + "/admin-template/src/main/java/com/luffy/mapper");
        configPathInfo.put("xml_path", System.getProperty("user.dir") + "/admin-template/src/main/resources/mybatis/mapper");
        configPathInfo.put("service_path", System.getProperty("user.dir") + "/admin-template/src/main/java/com/luffy/service");
        configPathInfo.put("service_impl_path", System.getProperty("user.dir") + "/admin-template/src/main/java/com/luffy/service/impl");
        configPathInfo.put("controller_path", System.getProperty("user.dir") + "/admin-template/src/main/java/com/luffy/api");
        // packageConfig.setPathInfo(configPathInfo);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/src/main/resources/mybatis/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/code/entity.java");
        templateConfig.setMapper("templates/code/mapper.java");
        templateConfig.setService("templates/code/service.java");
        templateConfig.setServiceImpl("templates/code/serviceImpl.java");
        templateConfig.setController("templates/code/controller.java");
        templateConfig.setXml(null);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        // 表名，多个英文逗号分割
        strategy.setInclude("user_role");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(packageConfig.getModuleName() + "_");

        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setCfg(cfg);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();
    }
}
