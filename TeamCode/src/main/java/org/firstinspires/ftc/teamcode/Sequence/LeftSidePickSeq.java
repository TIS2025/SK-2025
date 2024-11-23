package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class LeftSidePickSeq {
    private List<Action> LeftPick = new ArrayList<>();
    public LeftSidePickSeq(Intake intake, Outtake outtake, Hang hanger) {
//        LeftPick.clear();
//        LeftPick.add(
        Actions.runBlocking(

                new SequentialAction(
                        new ParallelAction(
                                intake.YawCommands(Intake.YawState.LEFT),
                                intake.ArmCommands(Intake.ArmState.DOWN),
                                outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                                outtake.BucketCommands(Outtake.BucketState.IN))



                )
        );
    }
}
