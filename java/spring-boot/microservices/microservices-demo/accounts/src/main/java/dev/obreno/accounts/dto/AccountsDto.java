package dev.obreno.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
            description = "Account number of Eazy Bank account",
            example = "3454433243"
    )
    @NotEmpty(message = "AccountNumber cannot be null or empty")
    @Pattern(regexp = "$|[0-9]{11}", message = "AccountNumber must be 11 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of Eazy Bank account",
            example = "Savings"
    )
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch address of Eazy Bank account",
            example = "123 New York"
    )
    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
}
