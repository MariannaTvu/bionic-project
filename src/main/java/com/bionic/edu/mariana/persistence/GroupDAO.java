package com.bionic.edu.mariana.persistence;

import java.util.List;

public interface GroupDAO {
    void add(Group group);
    void delete(long[] ids);
    Group findById(long id);
    List<Group> showUserGroups();

    List<Group> showAllGroups();
}
