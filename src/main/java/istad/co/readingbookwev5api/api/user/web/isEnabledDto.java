package istad.co.readingbookwev5api.api.user.web;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class isEnabledDto {

    @NotNull
    private Boolean status;
}
