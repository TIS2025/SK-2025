package org.firstinspires.ftc.teamcode.auto;

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
@Autonomous
public class SafeAuto extends LinearOpMode     {

    private RobotHardware robot;
    private Intake intake;
    private Outtake outtake;
    private Hang hanger;
    private MecanumDrive drive;


    @Override
    public void runOpMode() throws InterruptedException {

        robot = new RobotHardware();
        robot.init(hardwareMap, telemetry);
        intake = new Intake(robot);
        outtake = new Outtake(robot);
        hanger = new Hang(robot);
        drive = new MecanumDrive(hardwareMap, new Pose2d(-9, -60, Math.toRadians(90)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        new InitSequence(intake,outtake,hanger,1);



        TrajectoryActionBuilder HMAction = drive.actionBuilder(new Pose2d(-9, -60, Math.toRadians(90)))

                //PRELOAD
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(-9, -30), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(-9, -32), Math.toRadians(90))



                //First Sample

                .strafeToLinearHeading(new Vector2d(-44.5, -47), Math.toRadians(90))
                .afterTime(0.1, ()-> new ElbowExtendSeq(intake, outtake, hanger))
                .afterTime(0.1,()-> new PickSequenceAuto(intake, outtake, hanger))
                .afterTime(0.8, () -> new PickSeq(intake, outtake, hanger))

                .waitSeconds(2)
                .stopAndAdd( ()-> new TransferSequence(intake, outtake, hanger))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-65, -60), Math.toRadians(37))
                .waitSeconds(0.5)
                .stopAndAdd(()-> new DropHeightSequence(intake, outtake, hanger))
                .waitSeconds(1)
                .stopAndAdd(()-> new DropSequence(intake, outtake, hanger))



                //Second Sample

                .strafeToLinearHeading(new Vector2d(-54.8, -48.5), Math.toRadians(90))
                .afterTime(0.1, ()-> new ElbowExtendSeq(intake, outtake, hanger))
                .afterTime(0.1,()-> new PickSequenceAuto(intake, outtake, hanger))
                .afterTime(0.8, () -> new PickSeq(intake, outtake, hanger))
                .waitSeconds(2)
                .stopAndAdd(()-> new TransferSequence(intake, outtake, hanger))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(-65, -62), Math.toRadians(37))
                .waitSeconds(0.5)
                .stopAndAdd(()-> new DropHeightSequence(intake, outtake, hanger))
                .waitSeconds(1)
                .stopAndAdd(()-> new DropSequence(intake, outtake, hanger));



                //Third Sample



//
//                .strafeToLinearHeading(new Vector2d(-64, -45), Math.toRadians(90))
//                .afterTime(0.1, ()-> new ElbowExtendSeq(intake, outtake, hanger))
//                .afterTime(0.1,()-> new PickSequenceAuto(intake, outtake, hanger))
//                .afterTime(0.8, () -> new PickSeq(intake, outtake, hanger))
//                .waitSeconds(2)
//                .stopAndAdd(()-> new TransferSequence(intake, outtake, hanger))
//                .waitSeconds(0.5)
//                .strafeToLinearHeading(new Vector2d(-65, -62), Math.toRadians(37))
//                .waitSeconds(0.5)
//                .stopAndAdd(()-> new DropHeightSequence(intake, outtake, hanger))
//                .waitSeconds(1)
//                .stopAndAdd(()-> new AutoDropHSeq(intake, outtake, hanger));


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


        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        HMAction.build()
                )
        );


    }
}
