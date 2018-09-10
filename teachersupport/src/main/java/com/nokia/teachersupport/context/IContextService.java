package com.nokia.teachersupport.context;

import org.thymeleaf.context.Context;

public interface IContextService {
    void homeContext(Context context);
    void aboutMeContext(Context context);
    void publicationContext(Context context);
    void studentContext(Context context);
    void contactContext(Context context);
    void nullContext(Context context);
}
