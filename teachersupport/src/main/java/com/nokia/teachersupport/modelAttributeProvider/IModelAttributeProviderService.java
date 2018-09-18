package com.nokia.teachersupport.modelAttributeProvider;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface IModelAttributeProviderService {
    void adminDashboardModel(Model model, IServiceProvider serviceProvider);
    void generateModel(Model model);
    void aboutMeModel(Model model,IServiceProvider serviceProvider);
    void contactModel(Model model,IServiceProvider serviceProvider);
    void indexModel(Model model,IServiceProvider serviceProvider);
    void editProfileModel(Model model);
    void publicationModel(Model model,IServiceProvider serviceProvider);
    void homeModel(Model model,IServiceProvider serviceProvider);
    void studGroupModel(Model model, HttpSession session,IServiceProvider serviceProvider);
}
