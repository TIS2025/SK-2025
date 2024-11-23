package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

public class DoDrop {
    public DoDrop(Intake intake, Outtake outtake, Hang hanger) {
//        Drop.clear();
//        Drop.add(
        Actions.runBlocking(

                new SequentialAction(

                        outtake.BucketCommands(Outtake.BucketState.OUT)

                )
        );
    }
}
