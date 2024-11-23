
package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Intake;


public class IntakeMotorCommand {
    public IntakeMotorCommand(Intake intake, Intake.IntakeMotorState state) {
        Actions.runBlocking(new SequentialAction(
                new InstantAction(() -> intake.updateIntakeMotorState(state))
        ));
    }
}
