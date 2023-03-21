package com.switchfully.domain.models;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.switchfully.domain.models.Feature.*;

public enum Role {
    ADMIN(newArrayList(ADD_PROFESSOR, GET_ALL_PROFESSORS, GET_PROFESSOR_BY_ID, UPDATE_PASSWORD_BY_ID,
            DELETE_PROFESSOR_BY_ID, ADD_COURSE, GET_ALL_COURSES)),
    USER(newArrayList(GET_ALL_PROFESSORS, GET_PROFESSOR_BY_ID, GET_ALL_COURSES));
    private final List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }
}
