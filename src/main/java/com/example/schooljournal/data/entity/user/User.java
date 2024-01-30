package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.config.Permission;
import com.example.schooljournal.config.RolePermissionConfig;
import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.core.Address;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_user")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = User.class)
public class User extends BaseEntity implements UserDetails {

    @NotBlank
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Column(columnDefinition = "LONGTEXT")
    private String password;

    @NotBlank
    private String firstName;

    private String lastName;

    private Date birthDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private SchoolRole schoolRole;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return RolePermissionConfig.getPermissionsMap().get(schoolRole);
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return !isDeleted();
    }

    public boolean hasPermission(Permission permission) {
        return RolePermissionConfig.getPermissionsMap().get(schoolRole).contains(permission);
    }
}
