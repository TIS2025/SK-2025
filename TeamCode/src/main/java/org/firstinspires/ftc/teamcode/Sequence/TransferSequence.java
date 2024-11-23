package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;

public class TransferSequence {
    public ArrayList<Action> ftc = new ArrayList<>();
    public TransferSequence(Intake intake, Outtake outtake, Hang hanger) {
//        Transfer.clear();
//        Transfer.add(
//        ftc.clear();
//        ftc.add(
//                new SequentialAction(
//                        new InstantAction( () -> new ElbowCommand(intake, Intake.ElbowState.INIT)),
//                        new InstantAction( () -> new IntakeMotorCommand(intake, Intake.IntakeMotorState.OFF)),
//                        new InstantAction( () -> new LifterCommand(outtake, Outtake.LifterState.INTAKE_HEIGHT)),
//                        new InstantAction( () -> new BucketCommand(outtake, Outtake.BucketState.IN))
//                )
//        );

        Actions.runBlocking(

                new SequentialAction(
                        intake.ElbowCommands(Intake.ElbowState.INIT),
                        intake.IntakeMotorCommands(Intake.IntakeMotorState.OFF),
                        outtake.LifterCommands(Outtake.LifterState.INTAKE_HEIGHT),
                        outtake.BucketCommands(Outtake.BucketState.IN),
                        new ParallelAction(
                                intake.YawCommands(Intake.YawState.INIT),
                                intake.ArmCommands(Intake.ArmState.UP)
                                )

                )
        );
    }
}
