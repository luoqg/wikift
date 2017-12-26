/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wikift.support.validate.user;

import com.wikift.model.user.UserEntity;
import com.wikift.support.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserIsExistsValidator implements ConstraintValidator<UserIsExists, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UserIsExists userConstraint) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.isEmpty(field)) {
            try {
                Long userId = Long.valueOf(field);
                UserEntity entity = userService.getUserById(userId);
                return !ObjectUtils.isEmpty(entity);
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

}