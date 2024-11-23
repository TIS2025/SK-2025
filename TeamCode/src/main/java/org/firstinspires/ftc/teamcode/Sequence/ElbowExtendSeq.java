package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;

public class ElbowExtendSeq {

    public ArrayList<Action> ftc = new ArrayList<>();
    public ElbowExtendSeq(Intake intake, Outtake outtake, Hang hanger) {

        Actions.runBlocking(
                new SequentialAction(
                        outtake.LifterCommands(Outtake.LifterState.INTAKE_HEIGHT),
                        intake.ElbowCommands(Intake.ElbowState.FULL_EXTEND))
        );
//        ftc.clear();
//        ftc.add(
//                new SequentialAction(
//                        new InstantAction( () -> new ElbowCommand(intake, Intake.ElbowState.FULL_EXTEND))
//
//                )
//        );
    }
}
