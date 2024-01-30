package com.example.schooljournal.data.entity.user;

import com.example.schooljournal.data.entity.BaseEntity;
import com.example.schooljournal.data.entity.user.enums.SchoolRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "t_view")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class View extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private SchoolRole role;

    @ElementCollection
    @OrderColumn
    private List<String> componentIdsOrdered;
}
