package com.nokia.teachersupport.model;

import org.springframework.ui.Model;

public interface IModelService {
    void adminDashboardModel(Model model);
    void generateModel(Model model);
    void aboutMeModel(Model model);
    void contactModel(Model model);
    void indexModel(Model model);
    void editProfileModel(Model model);
    void publicationsModel(Model model);
    void homeModel(Model model);
}
