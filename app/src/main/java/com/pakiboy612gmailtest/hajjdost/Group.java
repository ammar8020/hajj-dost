package com.pakiboy612gmailtest.hajjdost;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Group {
    String groupName;

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
