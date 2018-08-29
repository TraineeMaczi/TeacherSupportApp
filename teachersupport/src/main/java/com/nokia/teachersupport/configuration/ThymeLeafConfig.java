package com.nokia.teachersupport.configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;
public enum ThymeLeafConfig {
    INSTANCE;
    private TemplateEngine templateEngine;

    private ThymeLeafConfig(){
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setPrefix(getTemplatePath());
        templateResolver.setTemplateMode("HTML");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    private String getTemplatePath(){
        return ThymeLeafConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "templatesToGenerate/";
    }

    public static TemplateEngine getTemplateEngine(){
        return INSTANCE.templateEngine;
    }
}
