package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Sequence.DropHeightSequence;
import org.firstinspires.ftc.teamcode.Sequence.DropSequence;
import org.firstinspires.ftc.teamcode.Sequence.ElbowExtendSeq;
import org.firstinspires.ftc.teamcode.Sequence.InitSequence;
import org.firstinspires.ftc.teamcode.Sequence.PickSeq;
import org.firstinspires.ftc.teamcode.Sequence.PickSequenceAuto;
import org.firstinspires.ftc.teamcode.Sequence.PreloadHangSeq;
import org.firstinspires.ftc.teamcode.Sequence.PreloadSpecimenSeq;
import org.firstinspires.ftc.teamcode.Sequence.TransferSequence;
import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

@Config
@Autonomous(name = "BasicAuto", group = "Autonomous")
public class BasicAuto extends LinearOpMode {

    private Intake intake;
    private Outtake outtake;
    private Hang hanger;
    private RobotHardware robot;



    @Override
    public void runOpMode() {

        robot = new RobotHardware();
        robot=RobotHardware.getInstance();
        robot.init(hardwareMap, telemetry);
        Pose2d intialPose = new Pose2d(-9, -60, Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap, intialPose);
        intake=new Intake(robot);
        outtake=new Outtake(robot);
        hanger=new Hang(robot);

        new InitSequence(intake, outtake, hanger,1);


        TrajectoryActionBuilder trajectoryAction1 = drive.actionBuilder(intialPose)
                //PRELOAD
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(-9, -30), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))



                //



//                .afterTime(0.5 , new InstantAction(() -> new IntakeSeq(intake, outtake, hanger)))
                .strafeToLinearHeading(new Vector2d(-45, -47.5), Math.toRadians(90))
                .afterTime(0.2, ()-> new ElbowExtendSeq(intake, outtake, hanger))
                .afterTime(0.3,()-> new PickSequenceAuto(intake, outtake, hanger))
                .waitSeconds(0.5)
                .afterTime(0.3, () -> new PickSeq(intake, outtake, hanger))

                .waitSeconds(0.8)
                .strafeToLinearHeading(new Vector2d(-46, -47.5), Math.toRadians(90))
                .waitSeconds(1.5)
                .stopAndAdd(new InstantAction(()-> new TransferSequence(intake, outtake, hanger)))
                .waitSeconds(0.5)

                .strafeToLinearHeading(new Vector2d(-62, -59), Math.toRadians(45))
                .stopAndAdd(new InstantAction(()-> new DropHeightSequence(intake, outtake, hanger)))
                .waitSeconds(1)
                .stopAndAdd(new InstantAction(()-> new DropSequence(intake, outtake)))




//                .stopAndAdd(new InstantAction(()-> new IntakeSeq(intake, outtake, hanger)))
                .strafeToLinearHeading(new Vector2d(-55.5, -49), Math.toRadians(90))
                .afterTime(0.2, new InstantAction(()-> new ElbowExtendSeq(intake, outtake, hanger)))
                .afterTime(0.3, new InstantAction(()-> new PickSequenceAuto(intake, outtake, hanger)))
                .waitSeconds(0.5)
                .afterTime(0.3, new InstantAction(() -> new PickSeq(intake, outtake, hanger)))
//                .afterTime(0.4, new InstantAction(() -> new PickSeq(intake, outtake, hanger)))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-56.5, -49), Math.toRadians(90))
                .waitSeconds(1.5)
                .stopAndAdd(new InstantAction(()-> new TransferSequence(intake, outtake, hanger)))
                .waitSeconds(0.5)

                .strafeToLinearHeading(new Vector2d(-62, -59), Math.toRadians(45))
                .stopAndAdd(new InstantAction(()-> new DropHeightSequence(intake, outtake, hanger)))
                .waitSeconds(1)
                .stopAndAdd(new InstantAction(()-> new DropSequence (intake, outtake)));
//                .stopAndAdd(new InstantAction(()-> new IntakeSeq(intake, outtake, hanger)))



//                .strafeToLinearHeading(new Vector2d(-64, -45), Math.toRadians(90))
//                .afterTime(0.2, new InstantAction(()-> new ElbowExtendSeq(intake, outtake, hanger)))
//                .afterTime(0.3, new InstantAction(()-> new PickSequenceAuto(intake, outtake, hanger)))
//                .waitSeconds(0.5)
//                .afterTime(0.3, new InstantAction(() -> new PickSeq(intake, outtake, hanger)))
////                .afterTime(0.4, new InstantAction(() -> new PickSeq(intake, outtake, hanger)))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-65.5, -45), Math.toRadians(90))
//                .waitSeconds(0.5)
//                .stopAndAdd(new InstantAction(()-> new TransferSequence(intake, outtake, hanger)))
//                .waitSeconds(0.5)
//
//                .strafeToLinearHeading(new Vector2d(-62, -59), Math.toRadians(45))
//                .stopAndAdd(new InstantAction(()-> new DropHeightSequence(intake, outtake, hanger)))
//                .waitSeconds(1)
//                .stopAndAdd(new InstantAction(()-> new DropSequence(intake, outtake, hanger)));


//                .strafeToLinearHeading(new Vector2d(-51, -51), Math.toRadians(45))
//
//                .strafeToLinearHeading(new Vector2d(-60, -47), Math.toRadians(90))
//                .strafeToLinearHeading(new Vector2d(-51, -51), Math.toRadians(45))



//        Action trajectoryAction0;
//        trajectoryAction0 = drive.actionBuilder(drive.pose)
//                .splineToConstantHeading(new Vector2d(-9,-33), Math.toRadians(90))
//                .waitSeconds(2)
//                .strafeToLinearHeading(new Vector2d(-40,-47), Math.toRadians(90))
//                .afterTime(1.0, new InstantAction(()-> new AutoIntakeSeq(intake, outtake, hanger)))
////                .afterTime(1.0, new InstantAction(0))
//                .waitSeconds(4)
//                .afterTime(1.0, new InstantAction(()-> new ElbowExtendSeq(intake, outtake, hanger)))
//                .afterTime(3.0, new InstantAction(()-> new TransferSequence(intake, outtake, hanger)))
////                .strafeToLinearHeading(new Vector2d(-51,-51), Math.toRadians(45))
////
////                .strafeToLinearHeading(new Vector2d(-50,-47), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(-51,-51), Math.toRadians(45))
////
////                .strafeToLinearHeading(new Vector2d(-60,-47), Math.toRadians(90))
////                .strafeToLinearHeading(new Vector2d(-51,-51), Math.toRadians(45))
//
//                .build();

        Action trajectoryAction2;
        trajectoryAction2 = drive.actionBuilder(drive.pose)
                .splineToConstantHeading(new Vector2d(0,33),Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(new Vector2d(0,43),Math.toRadians(-90)),Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(new Vector2d(56,56),Math.toRadians(-135)),Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(new Vector2d(49,37),Math.toRadians(-90)),Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(new Vector2d(56,56),Math.toRadians(-135)),Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(new Vector2d(58,37),Math.toRadians(-90)),Math.toRadians(0))
//                .lineToXLinearHeading(59,Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(new Vector2d(56,56),Math.toRadians(-90)),Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(new Vector2d(58,25),Math.toRadians(0)),Math.toRadians(0))
                .splineToLinearHeading(new Pose2d(new Vector2d(56,56),Math.toRadians(-90)),Math.toRadians(0))

                .splineToLinearHeading(new Pose2d(new Vector2d(24,0),Math.toRadians(-180)),Math.toRadians(0))

                .build();

        Action trajectoryAction3;
        trajectoryAction3 = drive.actionBuilder(drive.pose)
                .lineToY(33)

                .lineToYLinearHeading(37,Math.toRadians(-160))
                .setTangent(Math.toRadians(-160))
                .lineToY(56)


                .setTangent(Math.toRadians(-105))
                .lineToYLinearHeading(38,Math.toRadians(-90))

                .setTangent(Math.toRadians(-105))
                .lineToYLinearHeading(56,Math.toRadians(-135))
//
                .setTangent(Math.toRadians(-70))
                .lineToYLinearHeading(38,Math.toRadians(-90))

                .setTangent(Math.toRadians(-70))
                .lineToYLinearHeading(56,Math.toRadians(-135))

                .strafeToLinearHeading(new Vector2d(58,25),Math.toRadians(0))
                .strafeToLinearHeading(new Vector2d(56,56),Math.toRadians(-90))
                .setTangent(Math.toRadians(-125))
                .lineToX(24)
                .build();




        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1.build()
                )
        );


//            Action trajectoryActionChosen;
//            trajectoryActionChosen = trajectoryAction1;
//
//            if(gamepad1.a) {
//                Actions.runBlocking(
//                        new SequentialAction(
//                                trajectoryAction1
//                        )
//                );
//            }
//            if(gamepad1.b){
//                Actions.runBlocking(
//                        new SequentialAction(
//                                trajectoryAction3
////
//                        )
//                );
//            }
//            if(gamepad1.x){
//                Actions.runBlocking(
//                        new SequentialAction(
//                                trajectoryAction2
////
//                        )
//                );
//            }

    }
}