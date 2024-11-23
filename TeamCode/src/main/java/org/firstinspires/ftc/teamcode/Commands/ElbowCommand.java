
package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Intake;


public class ElbowCommand {
    public ElbowCommand(Intake intake, Intake.ElbowState state) {
        Actions.runBlocking(new SequentialAction(
                new InstantAction(() -> intake.updateElbowState(state))
        ));
    }
}
