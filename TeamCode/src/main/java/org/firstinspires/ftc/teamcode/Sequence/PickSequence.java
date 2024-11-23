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

public class PickSequence {
    private List<Action> PickIn = new ArrayList<>();
    public PickSequence(Intake intake, Outtake outtake, Hang hanger) {
//        PickIn.clear();
//        PickIn.add(
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                outtake.SpecimenCommands(Outtake.SpecimenState.INIT),
                                intake.YawCommands(Intake.YawState.INIT),
                                intake.ArmCommands(Intake.ArmState.DOWN),
                                outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                                outtake.BucketCommands(Outtake.BucketState.IN))

                )
        );
    }

}
