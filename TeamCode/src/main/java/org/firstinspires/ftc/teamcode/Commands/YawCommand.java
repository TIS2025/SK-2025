
package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Intake;


public class YawCommand {
    public YawCommand(Intake intake, Intake.YawState state) {
        Actions.runBlocking(new SequentialAction(
                new InstantAction(() -> intake.updateYawState(state))
        ));
    }
}
