package com.cc.spring.configuration;

import com.cc.spring.repository.TaskRepository;
import com.cc.spring.repository.TaskRepositoryImpl;
import com.cc.spring.repository.UserRepository;
import com.cc.spring.repository.UserRepositoryImpl;
import com.cc.spring.service.UserTaskService;
import com.cc.spring.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.cc.spring")
public class AppContext {

    @Bean
    public TaskRepository taskRepository()
    {
        return new TaskRepositoryImpl();
    }

    @Bean
    public UserRepository userRepository()
    {
        return new UserRepositoryImpl();
    }

    @Bean
    @Autowired
    public UserTaskService userTaskService(TaskRepository taskRepository, UserRepository userRepository) {
        UserTaskService service = new UserTaskServiceImpl();

        service.setTaskRepository(taskRepository);
        service.setUserRepository(userRepository);

        return service;
    }
}