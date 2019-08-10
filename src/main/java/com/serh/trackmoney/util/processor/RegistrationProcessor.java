package com.serh.trackmoney.util.processor;

import com.serh.trackmoney.dto.EntityDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.nonNull;

@NoArgsConstructor
@AllArgsConstructor
public abstract class RegistrationProcessor {

    @Setter
    public RegistrationProcessor nextProcessor;

    protected EntityDto dto;


    public abstract void validateOrElseThrow(EntityDto entityDto);

    protected void doProcess(final EntityDto dto) {
        if (nonNull(nextProcessor)) {
            nextProcessor.validateOrElseThrow(dto);
        }
    }
}
