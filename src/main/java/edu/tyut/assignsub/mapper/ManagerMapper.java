package edu.tyut.assignsub.mapper;

import edu.tyut.assignsub.pojo.Manager;

public interface ManagerMapper {

    Manager getManagerByUsername(String username);

    Manager getManager(String username, String password);

}
