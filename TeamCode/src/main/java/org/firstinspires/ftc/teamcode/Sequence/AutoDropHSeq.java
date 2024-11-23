package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class AutoDropHSeq {
    private List<Action> Drop = new ArrayList<>();
    public AutoDropHSeq(Intake intake, Outtake outtake, Hang hanger) {
//        Drop.clear();
//        Drop.add(
        Actions.runBlocking(

                new SequentialAction(
                        intake.YawCommands(Intake.YawState.INIT),

                        new ParallelAction(
                                intake.IntakeMotorCommands(Intake.IntakeMotorState.OFF),
                                intake.ArmCommands(Intake.ArmState.INIT),
                                intake.ElbowCommands(Intake.ElbowState.INIT)),
                        outtake.BucketCommands(Outtake.BucketState.OUT),
                        new SleepAction(1),
                        outtake.BucketCommands(Outtake.BucketState.IN),
                        outtake.LifterCommands(Outtake.LifterState.INIT)


                )
        );
    }
}
