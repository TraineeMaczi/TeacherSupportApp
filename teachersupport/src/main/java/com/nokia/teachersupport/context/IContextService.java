package com.nokia.teachersupport.context;

import com.nokia.teachersupport.serviceProvider.IServiceProvider;
import org.thymeleaf.context.Context;

public interface IContextService {
    void homeContext(Context context, IServiceProvider serviceProvider);
    void aboutMeContext(Context context,IServiceProvider serviceProvider);
    void publicationContext(Context context,IServiceProvider serviceProvider);
    void studentContext(Context context,IServiceProvider serviceProvider);
    void contactContext(Context context,IServiceProvider serviceProvider);
    void nullContext(Context context);
}
