package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class PickSeq {
    private List<Action> Pick = new ArrayList<>();
    public PickSeq(Intake intake, Outtake outtake, Hang hanger) {
//        Pick.clear();
//        Pick.add(
        Actions.runBlocking(
                new SequentialAction(
                        intake.ArmCommands(Intake.ArmState.LOCK),
                        intake.IntakeMotorCommands(Intake.IntakeMotorState.FORWARD),
                        outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                        outtake.BucketCommands(Outtake.BucketState.IN)
                )
        );
    }
    public PickSeq(Intake intake) {
//        Pick.clear();
//        Pick.add(
        Actions.runBlocking(
                new SequentialAction(
                        intake.ArmCommands(Intake.ArmState.DOWN),
                        intake.IntakeMotorCommands(Intake.IntakeMotorState.REVERSE)

                )
        );
    }
}
