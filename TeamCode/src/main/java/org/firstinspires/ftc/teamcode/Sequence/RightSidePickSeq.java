package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

public class RightSidePickSeq {

    public RightSidePickSeq(Intake intake, Outtake outtake, Hang hanger) {

        Actions.runBlocking(
                new SequentialAction(
                    intake.YawCommands(Intake.YawState.RIGHT),
                    intake.ArmCommands(Intake.ArmState.DOWN),
                    outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                    outtake.BucketCommands(Outtake.BucketState.IN))
        );
    }
}
