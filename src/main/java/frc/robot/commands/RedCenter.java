// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class RedCenter extends Command {
  /** Creates a new RedCenter. */
  public RedCenter() {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
        new Launch(drivetrain, shooter, intake),
        new IntakeStart(drivetrain, shooter, intake),
        new Trajectory1(drivetrain, shooter, intake),
        // new DriveStraight(4), // Use encoders if ultrasonic is broken
        new StopIntake(drivetrain, shooter, intake),
        new Trajectory2(drivetrain, shooter, intake),
        // new DriveStraight(-2), // Use Encoders if ultrasonic is broken
        new Launch(drivetrain, shooter, intake),
        Commands.parallel(
            new SetWristSetpoint(AutoConstants.kWristSetpoint, wrist), new CloseClaw(claw)));
  }
}

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
