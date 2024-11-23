package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;


public class GetValuesInitSequence {
    ArrayList<Action> GetValuesInit = new ArrayList<>();
    public GetValuesInitSequence(Intake intake, Outtake outtake,Hang hanger) {

//                GetValuesInit.clear();
//                GetValuesInit.add(
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                hanger.hangCommands(Hang.HangState.INIT),
                                outtake.LifterCommands(Outtake.LifterState.INIT),
                                intake.IntakeMotorCommands(Intake.IntakeMotorState.OFF)),

                        intake.ArmCommands(Intake.ArmState.INIT),
                        intake.YawCommands(Intake.YawState.INIT),
                        intake.ElbowCommands(Intake.ElbowState.SAFE),
                        outtake.BucketCommands(Outtake.BucketState.HALF),
                        outtake.SpecimenCommands(Outtake.SpecimenState.HALF)
                )
        );


    }
}
