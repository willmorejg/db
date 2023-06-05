/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.

James G Willmore - LJ Computing - (C) 2023
*/
package net.ljcomputing.db.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@Component
@Slf4j
public class Runner implements CommandLineRunner, ExitCodeGenerator {

    // auto-configured to inject PicocliSpringFactory
    private final IFactory factory;

    private final RunCommand runCommand;

    private int exitCode;

    public Runner(IFactory factory, RunCommand runCommand) {
        this.factory = factory;
        this.runCommand = runCommand;
    }

    /** {@inheritDoc} */
    @Override
    public void run(String... args) {
        try {
            exitCode = new CommandLine(runCommand, factory).execute(args);
        } catch (Exception e) {
            log.error("Error: ", e.getMessage());
        }

        if (exitCode != 0) {
            System.out.println("An error occured - check your arguments.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getExitCode() {
        return exitCode;
    }
}
