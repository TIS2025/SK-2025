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
import org.firstinspires.ftc.teamcode.Sequence.InitSequence;
import org.firstinspires.ftc.teamcode.Sequence.PreloadHangSeq;
import org.firstinspires.ftc.teamcode.Sequence.PreloadSpecimenSeq;
import org.firstinspires.ftc.teamcode.Sequence.SpecimenPick;
import org.firstinspires.ftc.teamcode.Sequence.SpecimenPickPose;
import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;
@Autonomous
public class ObservationZone extends LinearOpMode     {

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
        drive = new MecanumDrive(hardwareMap, new Pose2d(9, -60, Math.toRadians(90)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        new InitSequence(intake,outtake,hanger,1);



        TrajectoryActionBuilder HMAction = drive.actionBuilder(new Pose2d(9, -60, Math.toRadians(90)))

                //PRELOAD
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(9, -30), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(9, -32), Math.toRadians(90))

                //first specimen
                .afterTime(0.2, ()-> new SpecimenPickPose(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(36,-62), Math.toRadians(-90))
                .stopAndAdd(()-> new SpecimenPick(intake,outtake,hanger))
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(4,-28.7), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))


                //second Specimen
                .afterTime(0.2, ()-> new SpecimenPickPose(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(36,-62), Math.toRadians(-90))
                .stopAndAdd(()-> new SpecimenPick(intake,outtake,hanger))
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(12,-29), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger));




        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        HMAction.build()
                )
        );


    }
}
