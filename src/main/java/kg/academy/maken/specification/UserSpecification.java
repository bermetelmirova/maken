package kg.academy.maken.specification;

import kg.academy.maken.entity.User;
import kg.academy.maken.model.user_model.UserSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {
    private final UserSearch userSearch;

    @Override
    public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> userQuery, CriteriaBuilder userCriteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (userSearch.getLogin() != null) {
            predicates.add(userCriteriaBuilder
                    .like(userCriteriaBuilder
                            .lower(userRoot.get("login")),
                            "%" + userSearch.getLogin()
                                    .toLowerCase() + "%"));
        }

        return userCriteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
