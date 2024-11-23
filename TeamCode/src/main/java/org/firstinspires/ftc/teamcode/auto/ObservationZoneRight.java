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
@Autonomous(name = "Specimen Side"  )
public class ObservationZoneRight extends LinearOpMode     {

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
        drive = new MecanumDrive(hardwareMap, new Pose2d(11.5, -60, Math.toRadians(90)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        new InitSequence(intake,outtake,hanger,1);



        TrajectoryActionBuilder HMAction = drive.actionBuilder(new Pose2d(11.5, -60, Math.toRadians(90)))


                //PRELOAD
                .afterTime(0.1, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(8, -30), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))
                .splineToConstantHeading(new Vector2d(8, -35), Math.toRadians(90))
                .setReversed(true)

                .strafeToLinearHeading(new Vector2d(29,-35), Math.toRadians(90))

                .splineToLinearHeading(new Pose2d(48,-14 , Math.toRadians(90)),Math.toRadians(-40))
                .strafeToLinearHeading(new Vector2d(48,-55), Math.toRadians(90))

                .strafeToLinearHeading(new Vector2d(48,-35), Math.toRadians(-90))
                .splineToLinearHeading(new Pose2d(56,-12, Math.toRadians(-90)),Math.toRadians(-40))

                .strafeToLinearHeading(new Vector2d(56,-52), Math.toRadians(-90))

                .strafeToLinearHeading(new Vector2d(41,-52), Math.toRadians(-90))


                //first specimen
                .afterTime(0.2, ()-> new SpecimenPickPose(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(42,-64), Math.toRadians(-90))
                .stopAndAdd(()-> new SpecimenPick(intake,outtake,hanger))
                .waitSeconds(0.15)
                .afterTime(0.1, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(8.5,-37), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(8.5,-29.5), Math.toRadians(90))

                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))

                .strafeToLinearHeading(new Vector2d(8.5,-32), Math.toRadians(90))



                //second Specimen
                .afterTime(0.2, ()-> new SpecimenPickPose(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(31,-67), Math.toRadians(-90))
                .stopAndAdd(()-> new SpecimenPick(intake,outtake,hanger))
                .waitSeconds(0.15)
                .afterTime(0.2, ()-> new PreloadSpecimenSeq(intake, outtake, hanger))
                .strafeToLinearHeading(new Vector2d(3,-39), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(3,-30.5), Math.toRadians(90))
                .stopAndAdd(()-> new PreloadHangSeq(intake, outtake, hanger))
                .stopAndAdd(()-> outtake.LifterCommands(Outtake.LifterState.DOWN))
                .afterTime(0.2,()->new InitSequence(intake,outtake,hanger,1))
                .strafeToLinearHeading(new Vector2d(36,-62), Math.toRadians(90))
                .afterTime(0.1,()->new InitSequence(intake,outtake,hanger,1));






        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        HMAction.build()
                )
        );
        telemetry.addData("Lifter Position", robot.lifter.getCurrentPosition());


    }
}