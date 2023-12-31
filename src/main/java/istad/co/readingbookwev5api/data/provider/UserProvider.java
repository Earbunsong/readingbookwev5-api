package istad.co.readingbookwev5api.data.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public String buildUpdatePasswordWhereIdSql(){
        return new  SQL(){{
            UPDATE("users");
            SET("password = #{encodedPassword}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildInsertUserRoleSql(){
        return new SQL(){{
            INSERT_INTO("users_roles");
            VALUES("user_id","#{userId}");

        }}.toString();
    }
    public String buildInsertSql(){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("username","#{user.username}");
            VALUES("email","#{user.email}");
            VALUES("family_name","#{user.familyName}");
            VALUES("given_name","#{user.givenName}");
            VALUES("phone_number","#{use.phoneNumber}");
            VALUES("profile","#{user.profile.id}");
            VALUES("biography","#{user.biography}");
            VALUES("is_enabled","#{user.isEnabled}");
            VALUES("password","#{user.password}");
        }}.toString();
    }

    public String buildSelectByUsernameOrEmailSql(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("username = #{usernameOrEmail}", "is_enabled = #{isEnabled}");
            OR();
            WHERE("email = #{usernameOrEmail}","is_enabled = #{isEnabled}");
        }}.toString();
    }
    public String buildSelectUserProfileSql(){
        return new SQL(){{
            SELECT("i.id, i.uuid, i.extension, i.size, i.is_enabled");
            FROM("images AS i");
            WHERE("i.id =#{id}");
        }}.toString();
    }

    public String buildSelectUserRolesSql(){
        return new SQL(){{
            SELECT("r.id, r.name");
            FROM("roles AS r");
            INNER_JOIN("users_roles AS ur ON ur.roles_id = r.id");
            INNER_JOIN("users AS u ON u.id = ur.user_id");
            WHERE("u.id = #{id}");
        }}.toString();
    }
}
