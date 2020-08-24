package com.chama.app.models;

import javax.persistence.*;

@Entity
@SqlResultSetMapping(
        name = "MemberResult",
        classes = {
                @ConstructorResult(
                        targetClass = com.chama.app.models.Member.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "userForeignKey"),
                                @ColumnResult(name = "username")
                        }
                )
        }
)
@NamedNativeQuery(
        name = "Members",
        query = "select ui_user_integrations.id, ui_user_integrations.us_id_fk, us_users.us_username \n" +
                "from ui_user_integrations \n" +
                "inner join us_users on ui_user_integrations.us_id_fk = us_users.id \n" +
                "where ui_user_integrations.ch_id_fk = 1;",
        resultSetMapping = "MemberResult"
)
public class Member {

    private int id;
    private int userForeignKey;
    private String username;

    public Member(int id, int userForeignKey, String username) {
        this.id = id;
        this.userForeignKey = userForeignKey;
        this.username = username;
    }

    public Member() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserForeignKey() {
        return userForeignKey;
    }

    public void setUserForeignKey(int userForeignKey) {
        this.userForeignKey = userForeignKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
