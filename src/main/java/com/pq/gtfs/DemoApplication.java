
package com.pq.gtfs;


import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.google.protobuf.util.Timestamps.fromMillis;
import static java.lang.System.currentTimeMillis;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception  {

		SpringApplication.run(DemoApplication.class, args);

		//generateIncidentsStream();
		generateSchedulesStream();
		//generateVehicleEventsStream();

	}

	public static void generateIncidentsStream() throws IOException {
		FileOutputStream output;
		//////////////////////////////////////////////////////////////////////////////////////
		//									INCIDENTS
		/////////////////////////////////////////////////////////////////////////////////////
        com.pq.gtfs.models.Incidents.FeedMessage.Builder feedMessage = com.pq.gtfs.models.Incidents.FeedMessage.newBuilder();
		Timestamp timestampIncidents = fromMillis(currentTimeMillis());

		// --- FEED HEADER
        com.pq.gtfs.models.Incidents.FeedMessage.FeedHeader.Builder feedHeaderIdentity = com.pq.gtfs.models.Incidents.FeedMessage.FeedHeader.newBuilder();
		feedHeaderIdentity.setGtfsRealtimeVersionBytes(ByteString.copyFromUtf8("RealTimeVersion"));
		feedHeaderIdentity.setIncrementality(com.pq.gtfs.models.Incidents.FeedMessage.FeedHeader.Incrementality.FULL_DATASET);
		feedHeaderIdentity.setTimestamp(timestampIncidents);

        com.pq.gtfs.models.Incidents.FeedMessage.TripDescriptor.Builder tripDescriptor = com.pq.gtfs.models.Incidents.FeedMessage.TripDescriptor.newBuilder();
		tripDescriptor.setTripId("trip");
		tripDescriptor.setTripShortName("trip short name");
		tripDescriptor.setRouteId("route");
		tripDescriptor.setRouteShortName("route short name");
		tripDescriptor.setRouteLongName("route long name");
		tripDescriptor.setLineId("Linha");
		tripDescriptor.setLineShortName("Line short name");
		tripDescriptor.setLineLongName("Line long name");
		tripDescriptor.setExtraTripId("extra trip");
		tripDescriptor.setDeadRunId("dead run");
		tripDescriptor.setLayoverId("layover");
		tripDescriptor.setDirectionId(1);
		tripDescriptor.setStartTime("04:04");
		tripDescriptor.setStartDate("26/11/2021");
		tripDescriptor.setScheduleRelationship(com.pq.gtfs.models.Incidents.FeedMessage.TripDescriptor.ScheduleRelationship.ADDED);

        com.pq.gtfs.models.Incidents.FeedMessage.VehicleDescriptor.Builder vehicleDescriptor = com.pq.gtfs.models.Incidents.FeedMessage.VehicleDescriptor.newBuilder();
		vehicleDescriptor.setId("id");
		vehicleDescriptor.setBlockId("block");
		vehicleDescriptor.setDriverId("driver");
		vehicleDescriptor.setShiftId("shift");
		vehicleDescriptor.setLabel("label");
		vehicleDescriptor.setLicensePlate("plate");

		com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.Position.Builder position = com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.Position.newBuilder();
		position.setLatitude(Float.parseFloat("1.0001"));
		position.setLongitude(Float.parseFloat("8.0001"));
		position.setBearing(Float.parseFloat("3.0001"));
		position.setOdometer(Float.parseFloat("7.0001"));
		position.setSpeed(Float.parseFloat("80.0001"));

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.EntitySelector.Builder entitySelector = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.EntitySelector.newBuilder();
		entitySelector.setAgencyId("agency");

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.EntitySelector.Route.Builder route = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.EntitySelector.Route.newBuilder();
		route.setRouteShortName("route short name");
		route.setRouteLongName("route long name");
		route.setLineId("line");
		route.setLineShortName("line short name");
		route.setLineLongName("line long name");

		entitySelector.setRouteType(2);
		entitySelector.setTrip(tripDescriptor);
		entitySelector.setVehicle(vehicleDescriptor);

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TimeRange.Builder timeRange = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TimeRange.newBuilder();
		timeRange.setStart(timestampIncidents);
		timeRange.setEnd(timestampIncidents);

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.Builder alert = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.newBuilder();
		alert.setOperationPlanId("1");
		alert.setActivePeriod(timeRange);
		alert.setInformedEntity(entitySelector);

        com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.Builder vehiclePositionIncidents = com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.newBuilder();
		vehiclePositionIncidents.setTrip(tripDescriptor);
		vehiclePositionIncidents.setVehicle(vehicleDescriptor);
		vehiclePositionIncidents.setPosition(position);
		vehiclePositionIncidents.setCurrentStopSequence(1);
		vehiclePositionIncidents.setStopId("stop");
		vehiclePositionIncidents.setCurrentStatus(com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.VehicleStopStatus.IN_TRANSIT_TO);
		vehiclePositionIncidents.setTimestamp(4);
		vehiclePositionIncidents.setCongestionLevel(com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.CongestionLevel.CONGESTION);
		vehiclePositionIncidents.setOccupancyStatus(com.pq.gtfs.models.Incidents.FeedMessage.VehiclePosition.OccupancyStatus.EMPTY);

		alert.setCause(com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.Cause.ACCIDENT);
		alert.setEffect(com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.Effect.MODIFIED_SERVICE);

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TranslatedString.Translation.Builder translation = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TranslatedString.Translation.newBuilder();
		translation.setText("Tradução");
		translation.setLanguage("pt");

        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TranslatedString.Builder translatedString = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Alert.TranslatedString.newBuilder();
		translatedString.setTranslation(translation);

		alert.setUrl(translatedString);
		alert.setHeaderText(translatedString);
		alert.setDescriptionText(translatedString);

		//	--- FEED ENTITY
        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.Builder feedEntity = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.newBuilder();
		feedEntity.setId("Id1");
		feedEntity.setIsDeleted(false);
        com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.TripUpdate.Builder tripUpdateIdentity = com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.TripUpdate.newBuilder();
		tripUpdateIdentity.setTrip(tripDescriptor);

		tripUpdateIdentity.setVehicle(vehicleDescriptor);
		tripUpdateIdentity.setStopTimeUpdate(com.pq.gtfs.models.Incidents.FeedMessage.FeedEntity.TripUpdate.StopTimeUpdate.newBuilder().build());

		feedEntity.setTripUpdate(tripUpdateIdentity);
		feedEntity.setVehicle(vehiclePositionIncidents);

		feedMessage.setHeader(feedHeaderIdentity);
		feedMessage.setEntity(feedEntity);

		output = new FileOutputStream("/opt/Incidents.txt");
		System.out.println(JsonFormat.printer().print(feedMessage));
		feedMessage.build().writeTo(output);
		output.close();


		////////////////////////////////////////////////
		//  Descodificar de um ficheiro
		////////////////////////////////////////////////
		com.pq.gtfs.models.Incidents.FeedMessage readListIncidents = com.pq.gtfs.models.Incidents.FeedMessage.parseFrom(new FileInputStream("/opt/Incidents.txt"));

		//System.out.println(JsonFormat.printer().print(readListIncidents));


	}

	public static void generateSchedulesStream() throws IOException {
		FileOutputStream output;
		////////////////////////////////////////////////////////////////////////////////////
		//									SCHEDULES
		////////////////////////////////////////////////////////////////////////////////////
		com.pq.gtfs.models.Schedules.FeedMessage.Builder fms = com.pq.gtfs.models.Schedules.FeedMessage.newBuilder();

		// --- FEED HEADER
        com.pq.gtfs.models.Schedules.FeedMessage.FeedHeader.Builder fhs = com.pq.gtfs.models.Schedules.FeedMessage.FeedHeader.newBuilder();
		fhs.setGtfsRealtimeVersionBytes(ByteString.copyFromUtf8("RealTimeVersion"));
		fhs.setIncrementality(com.pq.gtfs.models.Schedules.FeedMessage.FeedHeader.Incrementality.FULL_DATASET);
		Timestamp timestampSchedules = fromMillis(currentTimeMillis());
		fhs.setTimestamp(1);

		com.pq.gtfs.models.Schedules.FeedMessage.TripDescriptor.Builder tds = com.pq.gtfs.models.Schedules.FeedMessage.TripDescriptor.newBuilder();
		tds.setTripId("trip");
		tds.setTripShortName("trip short name");
		tds.setRouteId("route");
		tds.setRouteShortName("route short name");
		tds.setRouteLongName("route long name");
		tds.setLineId("Linha");
		tds.setLineShortName("Line short name");
		tds.setLineLongName("Line long name");
		tds.setExtraTripId("extra trip");
		tds.setDirectionId(1);
		tds.setStartTime("04:04");
		tds.setStartDate("26/11/2021");
		tds.setPlatformCode("Plat Code");
		tds.setScheduleRelationship(com.pq.gtfs.models.Schedules.FeedMessage.TripDescriptor.ScheduleRelationship.ADDED);

		com.pq.gtfs.models.Schedules.FeedMessage.VehicleDescriptor.Builder vds = com.pq.gtfs.models.Schedules.FeedMessage.VehicleDescriptor.newBuilder();
		vds.setId("id");
		vds.setLabel("label");
		vds.setLicensePlate("plate");
		vds.setWheelchair(true);
		vds.setBicycles(true);

		com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.Position.Builder ps = com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.Position.newBuilder();
		ps.setLatitude(Float.parseFloat("1.0001"));
		ps.setLongitude(Float.parseFloat("8.0001"));
		ps.setBearing(Float.parseFloat("3.0001"));
		ps.setOdometer(Float.parseFloat("7.0001"));
		ps.setSpeed(Float.parseFloat("80.0001"));


		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TimeRange.Builder trs = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TimeRange.newBuilder();
		trs.setStart(1);
		trs.setEnd(2);

		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.EntitySelector.Builder ess = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.EntitySelector.newBuilder();
		ess.setAgencyId("Agency");
		ess.setRouteId("Route");
		ess.setRouteType(2);
		ess.setTrip(tds);
		ess.setStopId("stop");

		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.Builder as = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.newBuilder();
		as.setActivePeriod(trs);
		as.setInformedEntity(ess);

		com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.Builder vps = com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.newBuilder();
		vps.setTrip(tds);
		vps.setVehicle(vds);
		vps.setPosition(ps);
		vps.setCurrentStopSequence(1);
		vps.setStopId("stop");
		vps.setCurrentStatus(com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.VehicleStopStatus.IN_TRANSIT_TO);
		vps.setTimestamp(4);
		vps.setCongestionLevel(com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.CongestionLevel.CONGESTION);
		vps.setOccupancyStatus(com.pq.gtfs.models.Schedules.FeedMessage.VehiclePosition.OccupancyStatus.EMPTY);


		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TranslatedString.Translation.Builder ts = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TranslatedString.Translation.newBuilder();
		ts.setText("Tradução");
		ts.setLanguage("pt");


		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TranslatedString.Builder tss = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Alert.TranslatedString.newBuilder();
		tss.setTranslation(ts);

		as.setUrl(tss);
		as.setHeaderText(tss);
		as.setDescriptionText(tss);

		//	--- FEED ENTITY
		com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.Builder fes = com.pq.gtfs.models.Schedules.FeedMessage.FeedEntity.newBuilder();
		fes.setId("Id1");
		fes.setIsDeleted(false);

		com.pq.gtfs.models.Schedules.FeedMessage.TripUpdate.Builder tus = com.pq.gtfs.models.Schedules.FeedMessage.TripUpdate.newBuilder();
		tus.setOperationPlanId("Operation Plan");
		tus.setAgencyId("Agency");
		tus.setTrip(tds);
		tus.setVehicle(vds);
		tus.setStopTimeUpdate(com.pq.gtfs.models.Schedules.FeedMessage.TripUpdate.StopTimeUpdate.newBuilder().build());
		tus.setPlatformCode("Platform Code");
		tus.setTimestamp(1);
		tus.setDelay(4);

		fes.setTripUpdate(tus);
		fes.setVehicle(vps);

		fms.setHeader(fhs);
		fms.setEntity(fes);

		output = new FileOutputStream("/opt/Schedules.txt");
		//System.out.println(JsonFormat.printer().print(fms));
		fms.build().writeTo(output);
		output.close();

		com.pq.gtfs.models.Schedules.FeedMessage rls = com.pq.gtfs.models.Schedules.FeedMessage.parseFrom(new FileInputStream("/opt/Schedules.txt"));
		System.out.println(JsonFormat.printer().print(rls));
	}

	public static void generateVehicleEventsStream() throws IOException {

		FileOutputStream output;

		////////////////////////////////////////////////////////////////////////////////////
		//									VEHICLE EVENTS
		////////////////////////////////////////////////////////////////////////////////////

		com.pq.gtfs.models.VehicleEvents.FeedMessage.Builder feedMessage_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.newBuilder();
		Timestamp timestamp_VehicleEvents = fromMillis(currentTimeMillis());

		com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.Builder stu = com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.newBuilder();
		stu.setStopSequence(1);
		stu.setStopId("1111A");
		stu.setArrival(com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.StopTimeEvent.newBuilder().build());
		stu.setDeparture(com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.StopTimeEvent.newBuilder().build());
		stu.setScheduleRelationship(com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.ScheduleRelationship.SKIPPED);

		com.pq.gtfs.models.VehicleEvents.FeedMessage.TripDescriptor.Builder tripDescriptor_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.TripDescriptor.newBuilder();
		tripDescriptor_VehicleEvents.setTripId("trip 1");
		tripDescriptor_VehicleEvents.setRouteId("route 1");
		tripDescriptor_VehicleEvents.setLineId("line 1");
		tripDescriptor_VehicleEvents.setExtraTripId("extra 1");
		tripDescriptor_VehicleEvents.setLayoverId("layover 1");
		tripDescriptor_VehicleEvents.setDirectionId(1);
		tripDescriptor_VehicleEvents.setStartTime("00:00");
		tripDescriptor_VehicleEvents.setStartDate("2021-01-01");
		tripDescriptor_VehicleEvents.setScheduleRelationship(com.pq.gtfs.models.VehicleEvents.FeedMessage.TripDescriptor.ScheduleRelationship.SCHEDULED);

		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehicleDescriptor.Builder vehicleDescriptor_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehicleDescriptor.newBuilder();
		vehicleDescriptor_VehicleEvents.setId("Id1");
		vehicleDescriptor_VehicleEvents.setBlockId("Block1");
		vehicleDescriptor_VehicleEvents.setDriverId("Driver1");
		vehicleDescriptor_VehicleEvents.setShiftId("Shift1");
		vehicleDescriptor_VehicleEvents.setLabel("Label1");
		vehicleDescriptor_VehicleEvents.setLicensePlate("11-AA-11");

		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.Position.Builder position_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.Position.newBuilder();
		position_VehicleEvents.setLatitude(Float.parseFloat("11111.1"));
		position_VehicleEvents.setLongitude(Float.parseFloat("11111.1"));
		position_VehicleEvents.setBearing(Float.parseFloat("1.1"));
		position_VehicleEvents.setOdometer(Float.parseFloat("11111.1"));
		position_VehicleEvents.setSpeed(Float.parseFloat("111.1"));

		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.PassengerCounting.Builder passenger_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.PassengerCounting.newBuilder();
		passenger_VehicleEvents.setClassId("classId 1");
		passenger_VehicleEvents.setIncoming(111);
		passenger_VehicleEvents.setOutgoing(11);


		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.EcoDriving.Builder eco_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.EcoDriving.newBuilder();
		eco_VehicleEvents.setDistance(11111);
		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.EcoDriving.Consumption.Builder consumption_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.EcoDriving.Consumption.newBuilder();
		consumption_VehicleEvents.setType(com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.EcoDriving.Consumption.PropulsionTechnology.DIESEL);
		consumption_VehicleEvents.setValue(Float.parseFloat("3.1"));
		eco_VehicleEvents.setAverageSpeed(Float.parseFloat("3.1"));
		eco_VehicleEvents.setAverageAcceleration(Float.parseFloat("3.1"));
		eco_VehicleEvents.setSuddenAccelerations(1);
		eco_VehicleEvents.setAverageDeceleration(Float.parseFloat("3.1"));
		eco_VehicleEvents.setSuddenDecelerations(1);

		com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.Builder vehiclePosition_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.VehiclePosition.newBuilder();
		vehiclePosition_VehicleEvents.setOperationPlanId("opPlan 1");
		vehiclePosition_VehicleEvents.setAgencyId("agency 1");

		com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.Builder alert_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.newBuilder();
		com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.TimeRange.Builder timeRange_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.TimeRange.newBuilder();
		timeRange_VehicleEvents.setStart(timestamp_VehicleEvents);
		timeRange_VehicleEvents.setEnd(timestamp_VehicleEvents);
		alert_VehicleEvents.setActivePeriod(timeRange_VehicleEvents);

		com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.EntitySelector.Builder entitySelector_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.EntitySelector.newBuilder();
		entitySelector_VehicleEvents.setAgencyId("agency1");
		entitySelector_VehicleEvents.setRouteId("route1");
		entitySelector_VehicleEvents.setRouteType(3);
		entitySelector_VehicleEvents.setTrip(tripDescriptor_VehicleEvents);
		entitySelector_VehicleEvents.setStopId("stop1");

		alert_VehicleEvents.setInformedEntity(entitySelector_VehicleEvents);
		alert_VehicleEvents.setCause(com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.Cause.MAINTENANCE);
		alert_VehicleEvents.setEffect(com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Alert.Effect.ADDITIONAL_SERVICE);


// ---  FEED HEADER
		com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedHeader.Builder feedHeader_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedHeader.newBuilder();
		feedHeader_VehicleEvents.setGtfsRealtimeVersionBytes(ByteString.copyFromUtf8("RealTimeVersion"));
		feedHeader_VehicleEvents.setIncrementality(com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedHeader.Incrementality.FULL_DATASET);
		//Timestamp timestamp = fromMillis(currentTimeMillis());
		feedHeader_VehicleEvents.setTimestamp(timestamp_VehicleEvents);

//	--- FEED ENTITY
		com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.Builder feedEntity_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.FeedEntity.newBuilder();
		feedEntity_VehicleEvents.setId("Id1");
		feedEntity_VehicleEvents.setIsDeleted(false);
		com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.Builder tripUpdate_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.newBuilder();
		tripUpdate_VehicleEvents.setTrip(tripDescriptor_VehicleEvents);
		tripUpdate_VehicleEvents.setVehicle(vehicleDescriptor_VehicleEvents);
		tripUpdate_VehicleEvents.setStopTimeUpdate(com.pq.gtfs.models.VehicleEvents.FeedMessage.TripUpdate.StopTimeUpdate.newBuilder().build());

		feedEntity_VehicleEvents.setTripUpdate(tripUpdate_VehicleEvents);
		feedEntity_VehicleEvents.setVehicle(vehiclePosition_VehicleEvents);

		feedMessage_VehicleEvents.setHeader(feedHeader_VehicleEvents);
		feedMessage_VehicleEvents.setEntity(feedEntity_VehicleEvents);


		FileOutputStream output_VehicleEvents = new FileOutputStream("/opt/VehicleEvents.txt");
		feedMessage_VehicleEvents.build().writeTo(output_VehicleEvents);
		output_VehicleEvents.close();

		com.pq.gtfs.models.VehicleEvents.FeedMessage readList_VehicleEvents = com.pq.gtfs.models.VehicleEvents.FeedMessage.parseFrom(new FileInputStream("/opt/VehicleEvents.txt"));
		System.out.println(readList_VehicleEvents);

	}

}