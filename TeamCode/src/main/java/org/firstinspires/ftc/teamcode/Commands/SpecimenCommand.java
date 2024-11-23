
package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Outtake;


public class SpecimenCommand {
    public SpecimenCommand(Outtake outtake, Outtake.SpecimenState state) {
        Actions.runBlocking(new SequentialAction(
                new InstantAction(() -> outtake.updateSpecimenState(state))
        ));
    }
}
