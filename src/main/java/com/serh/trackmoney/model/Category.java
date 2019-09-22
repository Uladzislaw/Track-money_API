package com.serh.trackmoney.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.serh.trackmoney.dto.CategoryDto;
import com.serh.trackmoney.dto.Convertable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@Data
@Table(name = "categories")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Entity implements Convertable<Category, CategoryDto> {

    @NonNull
    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @NonNull
    private CategoryType type;

    @ManyToMany
    @JoinTable(
            name = "categories_users",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "u_id")})
    @JsonIgnore
    @ToString.Exclude
    private Set<User> users;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Consumption> consumption;

    @Column(name = "is_standard")
    private Boolean isStandard;

    @Override
    public CategoryDto toDto() {
        return CategoryDto.builder()
                .name(name)
                .type(type)
                .build();
    }
}
