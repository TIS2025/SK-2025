package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

public class AutoInitSeq {
    public AutoInitSeq(Intake intake, Outtake outtake, Hang hanger) {
//        Init.clear();
//        Init.add(
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                hanger.hangCommands(Hang.HangState.INIT),
                                outtake.LifterCommands(Outtake.LifterState.DOWN),
                                intake.IntakeMotorCommands(Intake.IntakeMotorState.OFF)),

                        intake.YawCommands(Intake.YawState.INIT),
                        intake.ArmCommands(Intake.ArmState.SAFE),
                        intake.ElbowCommands(Intake.ElbowState.INIT),
                        outtake.BucketCommands(Outtake.BucketState.HALF),
                        outtake.SpecimenCommands(Outtake.SpecimenState.RELEASE),
                        new SleepAction(1),
                        outtake.SpecimenCommands(Outtake.SpecimenState.GRIP)

                )
        );

//        public void runSequence(Intake intake, Outtake outtake, Hang hanger) {
//            if (!actionRunning) {
//                Actions.run(new SequentialAction(SpecimenHang)); // Run non-blocking action
//                actionRunning = true;
//            }
//        }
    }
}
