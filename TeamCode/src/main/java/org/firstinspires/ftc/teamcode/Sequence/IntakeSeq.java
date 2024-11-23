package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;

public class IntakeSeq  {

    public ArrayList<Action> ftc = new ArrayList<>();
    public IntakeSeq(Intake intake, Outtake outtake, Hang hanger) {


//                new SequentialAction(
//                        new InstantAction( () -> new ArmCommand(intake, Intake.ArmState.DOWN)),
//                        new InstantAction( () -> new YawCommand(intake, Intake.YawState.FULL_LEFT)),
//                        new InstantAction( () -> new IntakeMotorCommand(intake, Intake.IntakeMotorState.FORWARD)),
//                        new InstantAction( () -> new LifterCommand(outtake, Outtake.LifterState.TRANSFER)),
//                        new InstantAction( () -> new BucketCommand(outtake, Outtake.BucketState.IN))
//
//                )
//        );
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                intake.ArmCommands(Intake.ArmState.DOWN),
                                intake.YawCommands(Intake.YawState.FULL_LEFT),
                                intake.IntakeMotorCommands(Intake.IntakeMotorState.FORWARD),
                                outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                                outtake.BucketCommands(Outtake.BucketState.IN))

                )
        );

    }
}
