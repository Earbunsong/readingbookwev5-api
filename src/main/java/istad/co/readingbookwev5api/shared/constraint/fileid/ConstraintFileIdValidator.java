package istad.co.readingbookwev5api.shared.constraint.fileid;

import istad.co.readingbookwev5api.api.file.FileServiceImpl;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;

@RequiredArgsConstructor
public class ConstraintFileIdValidator implements ConstraintValidator<ConstraintFieId,Long> {

    private final FileServiceImpl fileServiceImpl;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return true;
    }



}
