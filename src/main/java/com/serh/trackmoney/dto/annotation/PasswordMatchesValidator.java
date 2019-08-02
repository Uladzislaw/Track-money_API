package com.serh.trackmoney.dto.annotation;

import com.serh.trackmoney.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(final PasswordMatches constraint) {
   }

   /**
    * Implements the validation logic.
    * The state of {@code value} must not be altered.
    * <p>
    * This method can be accessed concurrently, thread-safety must be ensured
    * by the implementation.
    *
    * @param value   object to validate
    * @param context context in which the constraint is evaluated
    * @return {@code false} if {@code value} does not pass the constraint
    */
   @Override
   public boolean isValid(final Object value, final ConstraintValidatorContext context) {
      UserDto userDto = (UserDto) value;
      return userDto.getPassword().equals(userDto.getMatchingPassword());
   }
}
