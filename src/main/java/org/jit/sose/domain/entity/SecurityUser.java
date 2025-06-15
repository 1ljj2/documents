package org.jit.sose.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 暂时没用到，存放用户的权限
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 请求权限集合
     */
    private Set<String> permissionURIs;

    public Set<String> getPermissionURIs() {
        return permissionURIs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public SecurityUser(Integer id, String username, String password, Set<String> roles, Set<String> urls) {
        this.setId(id);
        this.setUserName(username);
        this.setPassword(password);
        this.setRoles(roles);
        this.setPermissionURIs(urls);
    }

    public SecurityUser(Integer id, String username, String password, Set<String> roles,
                        Collection<? extends GrantedAuthority> authorities, Set<String> urls) {
        this.authorities = authorities;
        this.setId(id);
        this.setUserName(username);
        this.setPassword(password);
        this.setAuthorities(authorities);
        this.setRoles(roles);
        this.setPermissionURIs(urls);
    }

    public SecurityUser(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUserName());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setMail(user.getMail());
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否冻结
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 同getUserName()
     */
    @Override
    public String getUsername() {
        // 返回父类User的userName
        return this.getUserName();
    }

    public SecurityUser() {
        super();
    }

    /**
     * 获取userId
     */
    @Override
    public Integer getId() {
        return super.getId();
    }
}
