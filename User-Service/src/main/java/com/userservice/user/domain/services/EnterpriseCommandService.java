package com.userservice.user.domain.services;

import com.userservice.user.domain.model.aggregates.Enterprise;
import com.userservice.user.domain.model.commands.CreateEnterpriseCommand;
import com.userservice.user.domain.model.commands.UpdateEnterpriseCommand;

import java.util.Optional;

public interface EnterpriseCommandService {
    Optional<Enterprise> handle(CreateEnterpriseCommand createEnterpriseCommand);
    Optional<Enterprise> handle(UpdateEnterpriseCommand updateEnterpriseCommand);
}
